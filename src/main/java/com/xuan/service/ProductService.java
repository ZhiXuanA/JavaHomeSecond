package com.xuan.service;

import com.xuan.bean.*;

import java.util.List;

public interface ProductService {
    PageBean<Product> pageQuery(int currentPage, int pageSize, String cname);
    int addOne(String pname, String pnote, double pprice);
    Product findOne(int pno);
    List<Product> findAll();
    //删除一种商品（同时移除用户购物车内容
    void deleteOnePro(int pno);
    //改变商品内容
    void updateOne(String pname, String pnote, double pprice, int pno);

}
