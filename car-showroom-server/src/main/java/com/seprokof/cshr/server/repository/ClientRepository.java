package com.seprokof.cshr.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<com.seprokof.cshr.server.model.Client, Long> {

    Optional<com.seprokof.cshr.server.model.Client> findByPhoneNumber(String phoneNumber);

}
