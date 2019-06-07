package com.seprokof.cshr.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Orders")
@Data
public class OrderDto {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private CarDto car;
    @ManyToOne
    private ClientDto client;
    private Status status;

    public enum Status {
        RECIEVED, PROCESSING, COMPLETED
    }

}
