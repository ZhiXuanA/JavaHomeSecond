package com.xuan.service.impl;


import com.xuan.bean.OrderDetail;
import com.xuan.mapper.OrderMapper;
import com.xuan.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderServideImpl implements OderService {
    @Autowired
    OrderMapper oderDao;
    @Override
    public void addOne(String uname, String uemain, String addre, String car, double sum, String date) {
        oderDao.addOne(uname, uemain, addre, car, sum, date);
    }

    @Override
    public List<OrderDetail> findAll() {
        return oderDao.findAll();
    }

}
