package com.xuan.service;

import com.xuan.bean.*;

public interface UserService {
    //用户注册接口
    boolean regist(User user);
    User login(User user);


}
