package com.xuan.mapper;

import com.xuan.bean.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
public interface ProductMapper {
    @Select("select * from tab_product where pname like '%${pname}%' limit #{start},#{pageSize}")
    List<Product> findAll(int start, int pageSize, String pname);

    @Select("select count(*) from tab_product where pname like '%${pname}%'")
    int findTotal(@Param("pname")String pname);

    @Insert("insert into tab_product values (null,#{pname},#{pnote},#{pprice})")
    int addOne(String pname, String pnote, double pprice);

    @Select("select * from tab_product where pno=#{pno}")
    Product findOne(int pno);

    @Delete("delete from tab_product where pno=#{pno}")
    void delectOne(int pno);

    @Update("update tab_product set pname=#{pname},pnote=#{pnote},pprice=#{pprice} where pno=#{pno}")
    void updateOne(String pname, String pnote, double pprice, int pno);

    @Select("SELECT LAST_INSERT_ID()")
    int lastID();
}
