package com.xuan.service;

import com.xuan.bean.*;

import java.util.List;

public interface OderService {
    void addOne(String uname, String uemain, String addre, String car, double sum, String date);
    List<OrderDetail> findAll();
}
