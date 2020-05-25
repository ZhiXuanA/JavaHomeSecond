package com.xuan.mapper;

import com.xuan.bean.CarItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarMapper {
    @Insert("insert into tab_car values (#{username},#{pno})")
    void addCar(String username, int pno);

    //@Select("select * from tab_car where username=#{username}")
    @Select("SELECT pno,count(*) nums from tab_car where username=#{username} group by pno")
    List<CarItem> findAll(String username);

    @Delete("delete from tab_car where username='${username}' and pno=#{pno} limit 1")
    void subOne(String username, int pno);

    @Delete("delete from tab_car where username=#{username} and pno=#{pno}")
    void delectOne(String username, int pno);

    //删除一种商品（在商家删除一种商品后，要从用户的购物车移除)
    @Delete("delete from tab_car where pno=#{pno}")
    void delectOnePro(int pno);

}
