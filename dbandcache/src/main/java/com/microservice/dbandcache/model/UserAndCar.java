package com.microservice.dbandcache.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserAndCar extends User {
    private List<Car> cars;

}
