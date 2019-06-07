package com.seprokof.cshr.server;

import static com.seprokof.cshr.server.PojoConverter.fromCarDto;
import static com.seprokof.cshr.server.PojoConverter.fromOrderDto;
import static com.seprokof.cshr.server.PojoConverter.toCarDto;
import static com.seprokof.cshr.server.PojoConverter.toClientDto;
import static com.seprokof.cshr.server.PojoConverter.toOptionDto;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seprokof.cshr.server.model.CarDto;
import com.seprokof.cshr.server.model.ClientDto;
import com.seprokof.cshr.server.model.OptionDto;
import com.seprokof.cshr.server.model.OrderDto;
import com.seprokof.cshr.server.model.OrderDto.Status;
import com.seprokof.cshr.server.repository.CarRepository;
import com.seprokof.cshr.server.repository.ClientRepository;
import com.seprokof.cshr.server.repository.OptionRepository;
import com.seprokof.cshr.server.repository.OrderRepository;

@Service
public class OrderApiDelegateImpl implements OrderApiDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApiDelegateImpl.class);

    private final OptionRepository optionRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderApiDelegateImpl(OptionRepository optionRepository, CarRepository carRepository,
            ClientRepository clientRepository, OrderRepository orderRepository) {
        this.optionRepository = optionRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity<Void> createOrder(Order order) {
        OrderDto orderToSave = new OrderDto();

        Set<OptionDto> options = findOrSaveOptions(order.getCar().getOptions());

        CarDto car = findOrSaveCar(order.getCar(), options);
        orderToSave.setCar(car);

        ClientDto client = clientRepository.findByPhoneNumber(order.getClient().getPhone())
                .orElseGet(() -> clientRepository.save(toClientDto(order.getClient())));
        orderToSave.setClient(client);

        orderToSave.setStatus(Status.RECIEVED);
        orderRepository.save(orderToSave);
        LOGGER.debug("{} created", orderToSave);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Set<OptionDto> findOrSaveOptions(List<Option> options) {
        return Optional
                .ofNullable(options).orElse(new ArrayList<>()).stream().map(opt -> optionRepository
                        .findByCode(opt.getCode()).orElseGet(() -> optionRepository.save(toOptionDto(opt))))
                .collect(toSet());
    }

    private CarDto findOrSaveCar(Car car, Set<OptionDto> options) {
        return carRepository.findByBrandAndModel(car.getBrand(), car.getModel()).stream()
                .filter(c -> c.getOptions().equals(options)).findAny()
                .orElseGet(() -> carRepository.save(toCarDto(car, options)));
    }

    @Override
    public ResponseEntity<Void> updateOrder(Long orderId, Order updatedOrder) {
        Optional<OrderDto> optOrder = orderRepository.findById(orderId);
        if (!optOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean isUpdated = false;
        OrderDto order = optOrder.get();

        /*
         * The only update allowed from client perspective is that another existing client assigned for particular
         * order. Updates of client information such as first, last names should be done using another API.
         * 
         * Thus, if phone number of currently assigned client matches phone number in updated order we considering that
         * client left unchanged.
         * 
         */
        if (!order.getClient().getPhoneNumber().equals(updatedOrder.getClientPhone())) {
            Optional<ClientDto> optClient = clientRepository.findByPhoneNumber(updatedOrder.getClientPhone());
            if (!optClient.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            order.setClient(optClient.get());
            isUpdated = true;
        } else {
            LOGGER.trace("No changes in client information");
        }

        /*
         * Car could be updated in a various ways - all fields except id could be changed. If inexistent option found it
         * will be saved in repository. Generally it is security breach, but this is only sample application.
         * 
         */
        Car carFromReq = updatedOrder.getCar();
        if (fromCarDto(order.getCar()).equals(carFromReq)) {
            LOGGER.trace("No changes in car information");
        } else {
            Set<OptionDto> options = findOrSaveOptions(carFromReq.getOptions());
            CarDto car = findOrSaveCar(carFromReq, options);
            order.setCar(car);
            isUpdated = true;
        }

        if (isUpdated) {
            orderRepository.save(order);
            LOGGER.debug("{} updated", order);
        } else {
            LOGGER.debug("No changes in order '{}' information were done", orderId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> getOrder(Long orderId) {
        Optional<OrderDto> optOrder = orderRepository.findById(orderId);
        if (!optOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = fromOrderDto(optOrder.get());
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(PojoConverter::fromOrderDto).collect(toList());
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

}
