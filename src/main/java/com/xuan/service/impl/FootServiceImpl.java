package com.xuan.service.impl;


import com.xuan.mapper.*;
import com.xuan.bean.*;
import com.xuan.service.FootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FootServiceImpl implements FootService {
    @Autowired
    FootMapper footDao;
    @Override
    public void addOne(String uname, int pno) {
        if(footDao.findOne(uname,pno)>0)
            footDao.updateOne(uname,pno);
        else
            footDao.addOne(uname,pno);
    }

    @Override
    public List<FootBean> findAll() {
        return footDao.findAll();
    }
}
