package com.xuan.mapper;

import com.xuan.bean.FootBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FootMapper {
    @Insert("insert into tab_foot values (#{uname},10,#{pno})")
    void addOne(String uname, int pno);

    @Select("select count(*) from tab_foot where uname=#{uname} and pno=#{pno}")
    int findOne(String uname, int pno);

    @Update("UPDATE tab_foot SET time=time+10 where uname=#{uname} and pno=#{pno}")
    void updateOne(String uname, int pno);

    @Select("select * from tab_foot")
    List<FootBean> findAll();
}
