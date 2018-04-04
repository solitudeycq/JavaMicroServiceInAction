package com.microservice.dbandcache.service;

import com.microservice.dbandcache.dao.CarDao;
import com.microservice.dbandcache.dao.UserDao;
import com.microservice.dbandcache.model.Car;
import com.microservice.dbandcache.model.User;
import com.microservice.dbandcache.model.UserAndCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CarDao carDao;

    public User getUser(long id) {
        return userDao.selectByPrimaryKey(id);
    }

    public UserAndCar getUserAndCar(long userId){
        UserAndCar userAndCar = new UserAndCar();
        User user = userDao.selectByPrimaryKey(userId);
        if(user!=null){
            List<Car> cars = carDao.selectByOwner(userId);
            userAndCar.setId(user.getId());
            userAndCar.setName(user.getName());
            userAndCar.setPhone(user.getPhone());
            userAndCar.setCars(cars);
        }
        return userAndCar;
    }
}
