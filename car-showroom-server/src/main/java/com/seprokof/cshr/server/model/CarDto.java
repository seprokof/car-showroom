package com.seprokof.cshr.server.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class CarDto {

    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private String model;
    @ManyToMany
    private List<OptionDto> options;

}
