package com.xuan;

import com.xuan.mapper.TuijianDao;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    //数据库连接池
    void contextLoads()  throws Exception{
        Connection connection=dataSource.getConnection();
        PreparedStatement ps=connection.prepareStatement("select * from tab_user");
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            String name=rs.getString("username");
            System.out.println(name);
        }
    }
    //测试推荐
    @Autowired
    TuijianDao DD;
    void testData(String username){
        int favPno=DD.getFavoutPno(username);
        String[] names=DD.getSamePeople(favPno,username);
        System.out.println(Arrays.toString(names));
       DD.getMap(names);

    }

    @Test
    void mTest(){
        testData("A");
    }



}
