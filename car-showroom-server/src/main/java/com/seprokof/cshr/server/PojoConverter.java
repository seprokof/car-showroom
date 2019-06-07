package com.seprokof.cshr.server;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Set;

import com.seprokof.cshr.server.model.CarDto;
import com.seprokof.cshr.server.model.ClientDto;
import com.seprokof.cshr.server.model.OptionDto;
import com.seprokof.cshr.server.model.OrderDto;

/**
 * Performs POJO conversion.
 * 
 * @author seprokof
 *
 */
public final class PojoConverter {

    private PojoConverter() {

    }

    public static OptionDto toOptionDto(Option option) {
        OptionDto result = new OptionDto();
        result.setCode(option.getCode());
        result.setDescription(option.getDescription());
        return result;
    }

    public static CarDto toCarDto(Car car, Set<OptionDto> options) {
        CarDto result = new CarDto();
        result.setBrand(car.getBrand());
        result.setModel(car.getModel());
        result.setOptions(new ArrayList<>(options));
        return result;
    }

    public static ClientDto toClientDto(Client client) {
        ClientDto result = new ClientDto();
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        result.setPhoneNumber(client.getPhone());
        result.setVip(client.getVip());
        return result;
    }

    public static Option fromOptionDto(OptionDto option) {
        Option result = new Option();
        result.setCode(option.getCode());
        result.setDescription(option.getDescription());
        return result;
    }

    public static Car fromCarDto(CarDto car) {
        Car result = new Car();
        result.setBrand(car.getBrand());
        result.setModel(car.getModel());
        result.setOptions(car.getOptions().stream().map(PojoConverter::fromOptionDto).collect(toList()));
        return result;
    }

    public static Client fromClientDto(ClientDto client) {
        Client result = new Client();
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        result.setPhone(client.getPhoneNumber());
        result.setVip(client.getVip());
        return result;
    }

    public static Order fromOrderDto(OrderDto order) {
        Order result = new Order();
        result.car(fromCarDto(order.getCar()));
        Client client = fromClientDto(order.getClient());
        result.setClient(client);
        result.setClientPhone(client.getPhone());
        return result;
    }

}
