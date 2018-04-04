package com.microservice.dbandcache.dao;

import com.microservice.dbandcache.config.DatabaseContextHolder;
import com.microservice.dbandcache.config.DatabaseType;
import com.microservice.dbandcache.mapper.CarMapper;
import com.microservice.dbandcache.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDao {
    @Autowired
    private CarMapper carMapper;
    public List<Car> selectByOwner(long ownerId){
        DatabaseContextHolder.setDatabaseType(DatabaseType.microservicedb2);
        return carMapper.selectByOwner(ownerId);
    }

}
