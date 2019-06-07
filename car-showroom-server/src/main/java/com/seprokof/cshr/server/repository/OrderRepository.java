package com.seprokof.cshr.server.repository;

import org.springframework.data.repository.CrudRepository;

import com.seprokof.cshr.server.model.OrderDto;

/**
 * Manages {@link OrderDto}.
 * 
 * @author seprokof
 *
 */
public interface OrderRepository extends CrudRepository<OrderDto, Long> {

}
