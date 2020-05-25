package com.xuan.mapper;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface TuijianDao {
    //查询最喜欢的商品
    @Select("SELECT pno FROM tab_foot WHERE uname=#{uname} order by time DESC LIMIT 1;")
    int getFavoutPno(String uname);
    //查询有共同爱好的用户
    @Select("SELECT uname FROM tab_foot WHERE PNO=#{pno}  AND uname!=#{uname}")
    String[] getSamePeople(int pno,String uname);

    //查询每种商品及统计和
    @Select("SELECT pno,sum(time) FROM tab_foot WHERE uname in ${names} GROUP BY pno")
    Map<Integer,Integer> getMap(String[] names);
}
