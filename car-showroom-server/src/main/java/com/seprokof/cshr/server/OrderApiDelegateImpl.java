package com.seprokof.cshr.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

        List<OptionDto> options = Optional
                .ofNullable(order.getCar().getOptions()).orElse(new ArrayList<>()).stream().map(opt -> optionRepository
                        .findByCode(opt.getCode()).orElseGet(() -> optionRepository.save(toModelOption(opt))))
                .collect(Collectors.toList());

        CarDto car = carRepository
                .findByBrandAndModelAndOptions(order.getCar().getBrand(), order.getCar().getModel(), options)
                .orElseGet(() -> carRepository.save(toModelCar(order.getCar(), options)));
        orderToSave.setCar(car);

        ClientDto client = clientRepository.findByPhoneNumber(order.getClient().getPhone())
                .orElseGet(() -> clientRepository.save(toModelClient(order.getClient())));
        orderToSave.setClient(client);

        orderToSave.setStatus(Status.RECIEVED);
        orderRepository.save(orderToSave);
        LOGGER.debug("{} created", orderToSave);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static OptionDto toModelOption(Option option) {
        OptionDto result = new OptionDto();
        result.setCode(option.getCode());
        result.setDescription(option.getDescription());
        return result;
    }

    private static CarDto toModelCar(Car car, List<OptionDto> options) {
        CarDto result = new CarDto();
        result.setBrand(car.getBrand());
        result.setModel(car.getModel());
        result.setOptions(options);
        return result;
    }

    private static ClientDto toModelClient(Client client) {
        ClientDto result = new ClientDto();
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        result.setPhoneNumber(client.getPhone());
        result.setVip(client.getVip());
        return result;
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
        Order order = fromModelOrder(optOrder.get());
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    private static Option fromModelOption(OptionDto option) {
        Option result = new Option();
        result.setCode(option.getCode());
        result.setDescription(option.getDescription());
        return result;
    }

    private static Car fromModelCar(CarDto car) {
        Car result = new Car();
        result.setBrand(car.getBrand());
        result.setModel(car.getModel());
        result.setOptions(
                car.getOptions().stream().map(OrderApiDelegateImpl::fromModelOption).collect(Collectors.toList()));
        return result;
    }

    private static Client fromModelClient(ClientDto client) {
        Client result = new Client();
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        result.setPhone(client.getPhoneNumber());
        result.setVip(client.getVip());
        return result;
    }

    private static Order fromModelOrder(OrderDto order) {
        Order result = new Order();
        result.car(fromModelCar(order.getCar()));
        Client client = fromModelClient(order.getClient());
        result.setClient(client);
        result.setClientPhone(client.getPhone());
        return result;
    }

    @Override
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(OrderApiDelegateImpl::fromModelOrder).collect(Collectors.toList());
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

}
