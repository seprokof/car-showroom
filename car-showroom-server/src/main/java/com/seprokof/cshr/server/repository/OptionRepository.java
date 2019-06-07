package com.seprokof.cshr.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.seprokof.cshr.server.model.OptionDto;

/**
 * Manages {@link OptionDto}.
 * 
 * @author seprokof
 *
 */
public interface OptionRepository extends CrudRepository<OptionDto, Long> {

    /**
     * Retrieves car option by it's code.
     * 
     * @param code
     *            the code of option
     * @return {@link Optional} which contains car option with given code.
     */
    Optional<OptionDto> findByCode(String code);

}
