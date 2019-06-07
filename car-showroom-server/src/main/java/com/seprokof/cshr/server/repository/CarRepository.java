package com.seprokof.cshr.server.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.seprokof.cshr.server.model.CarDto;

/**
 * Manages {@link CarDto}.
 * 
 * @author seprokof
 *
 */
public interface CarRepository extends CrudRepository<CarDto, Long> {

    /**
     * Retrieves cars by given brand and model.
     * 
     * @param brand
     *            the brand of the car
     * @param model
     *            the model of the car
     * @return {@link List} which contains cars matched given criteria or empty list
     */
    List<CarDto> findByBrandAndModel(String brand, String model);

}
