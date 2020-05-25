package com.xuan.service.impl;


import com.xuan.bean.User;
import com.xuan.mapper.UserMapper;
import com.xuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;
    @Override
    public boolean regist(User user) {
        //进行注册并根据结果返回true或者false
        User u=userDao.findById(user.getUsername());
        if(u!=null)
        {
            return false;
        }
        else {
            userDao.addUser(user);
            return true;
        }
    }

    //用户登录功能
    @Override
    public User login(User user) {
        User u=userDao.loginUer(user.getUsername(),user.getPassword());
        return u;
    }


}
