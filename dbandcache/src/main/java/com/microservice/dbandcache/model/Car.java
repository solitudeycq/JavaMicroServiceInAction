package com.microservice.dbandcache.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private Long id;

    private String name;

    private String color;

    private Long owner;

}