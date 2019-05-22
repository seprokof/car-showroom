package com.seprokof.cshr.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<com.seprokof.cshr.server.model.Car, Long> {

    Optional<com.seprokof.cshr.server.model.Car> findByBrandAndModelAndOptions(String brand, String model,
            List<com.seprokof.cshr.server.model.Option> options);

}
