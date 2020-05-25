package com.xuan.mapper;

import com.xuan.bean.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    @Insert("insert into tab_order (`uname`, `uemail`, `addre`, `car`, `sum`, `date`) " +
            "VALUES (#{uname},#{uemain},#{addre},#{car},#{sum},#{date})")
    void addOne(String uname, String uemain, String addre, String car, double sum, String date);

    @Select("select * from tab_order")
    List<OrderDetail> findAll();
}
