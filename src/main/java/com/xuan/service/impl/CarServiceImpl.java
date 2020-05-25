package com.xuan.service.impl;


import com.xuan.bean.CarBean;
import com.xuan.mapper.CarMapper;
import com.xuan.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xuan.bean.*;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarMapper carDao;

    @Override
    public void addCar(String username, int pno) {
        carDao.addCar(username,pno);
    }

    @Override
    public void subOne(String username, int pno) {
        carDao.subOne(username,pno);
    }


    @Override
    public void delectOne(String username, int pno) {
        carDao.delectOne(username,pno);
    }

    @Override
    public CarBean findAll(String username) {
        List<CarItem> carItems=carDao.findAll(username);
        CarBean carBean=new CarBean();
        carBean.setCarObject(carItems);
        return carBean;

    }
}
