package com.seprokof.cshr.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.seprokof.cshr.server.model.CarDto;
import com.seprokof.cshr.server.model.OptionDto;

/**
 * Manages {@link CarDto}.
 * 
 * @author seprokof
 *
 */
public interface CarRepository extends CrudRepository<CarDto, Long> {

    /**
     * Retrieves car by given brand, model and collection of options.
     * 
     * @param brand
     *            the brand of the car
     * @param model
     *            the model of the car
     * @param options
     *            additional options
     * @return {@link Optional} which contains car matched given criteria
     */
    Optional<CarDto> findByBrandAndModelAndOptions(String brand, String model, List<OptionDto> options);

}
