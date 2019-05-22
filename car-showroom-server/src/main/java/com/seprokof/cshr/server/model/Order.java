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
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Client client;
    private Status status;

    public enum Status {
        RECIEVED, PROCESSING, COMPLETED
    }

}
