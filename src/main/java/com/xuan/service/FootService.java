package com.xuan.service;

import com.xuan.bean.*;

import java.util.List;

public interface FootService {
    void addOne(String uname, int pno);
    List<FootBean> findAll();
}
