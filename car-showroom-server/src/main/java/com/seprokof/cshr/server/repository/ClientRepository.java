package com.seprokof.cshr.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.seprokof.cshr.server.model.ClientDto;

/**
 * Manages {@link ClientDto}.
 * 
 * @author seprokof
 *
 */
public interface ClientRepository extends CrudRepository<ClientDto, Long> {

    /**
     * Retrieves client by phone number.
     * 
     * @param phoneNumber
     *            the phone number of the client
     * @return {@link Optional} which contains client owning given phone number
     */
    Optional<ClientDto> findByPhoneNumber(String phoneNumber);

}
