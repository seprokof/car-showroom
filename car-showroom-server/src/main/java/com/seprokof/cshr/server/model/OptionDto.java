package com.seprokof.cshr.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class OptionDto {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String code;
    private String description;

}
