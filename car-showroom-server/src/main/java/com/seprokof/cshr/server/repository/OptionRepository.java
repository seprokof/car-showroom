package com.seprokof.cshr.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<com.seprokof.cshr.server.model.Option, Long> {

    Optional<com.seprokof.cshr.server.model.Option> findByCode(String code);

}
