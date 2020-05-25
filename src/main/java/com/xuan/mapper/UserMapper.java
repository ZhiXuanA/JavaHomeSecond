package com.xuan.mapper;

import com.xuan.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    @Select("select * from tab_user where username=#{username}")
    User findById(String username);
    @Insert("INSERT INTO `tab_user` (`username`, `password`, `name`, `telephone`,  `sex`,`birthday`)" +
            " VALUES(#{username},#{password},#{name},#{telephone},#{sex},#{birthday})")
    void addUser(User user);
    //用户登录功能
    @Select("select * from tab_user where username=#{username} and password=#{password}")
    User loginUer(String username, String password);
}
