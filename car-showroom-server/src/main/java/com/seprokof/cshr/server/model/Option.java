package com.seprokof.cshr.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Option {

    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String description;

}
