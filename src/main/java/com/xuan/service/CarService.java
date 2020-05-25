package com.xuan.service;

import com.xuan.bean.*;

public interface CarService {
    //数量加1
    void addCar(String username, int pno);
    //获取整个购物车
    CarBean findAll(String username);
    //数量减1
    void subOne(String username, int pno);
    //删除一个
    void delectOne(String username, int pno);

}
