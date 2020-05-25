package com.xuan.mapper;

import com.xuan.bean.CarItem;

import java.util.List;

public interface CarDao {
    public void addCar(String username, int pno);
    List<CarItem> findAll(String username);
    void subOne(String username, int pno);
    void delectOne(String username, int pno);
    //删除一种商品（在商家删除一种商品后，要从用户的购物车移除)
    void delectOnePro(int pno);
}
