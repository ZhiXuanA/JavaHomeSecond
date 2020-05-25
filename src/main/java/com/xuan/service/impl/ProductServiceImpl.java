package com.xuan.service.impl;

import com.xuan.bean.PageBean;
import com.xuan.bean.Product;


import com.xuan.mapper.CarMapper;
import com.xuan.mapper.ProductMapper;
import com.xuan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productDao;
    @Autowired
    private CarMapper carDao;
    @Override
    public PageBean<Product> pageQuery(int currentPage, int pageSize, String pname) {
        PageBean<Product> pageBean=new PageBean<>();
        int start=(currentPage-1)*pageSize;
        List<Product> products=productDao.findAll(start,pageSize,pname);
//        for (Product product : products) {
//            System.out.println(product);
//        }
        int total=productDao.findTotal(pname);
        pageBean.setList(products);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(total);
        int totalPage=(total%pageSize==0)?(total/pageSize):(total/pageSize)+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public int addOne(String pname, String pnote, double pprice) {
        productDao.addOne(pname,pnote,pprice);
        return productDao.lastID();
    }

    @Override
    public Product findOne(int pno) {
        return productDao.findOne(pno);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products=productDao.findAll(0,1000,"");
        return products;
    }

    @Override
    public void deleteOnePro(int pno) {
        carDao.delectOnePro(pno);
        productDao.delectOne(pno);
    }

    @Override
    public void updateOne(String pname, String pnote, double pprice, int pno) {
        Product old=productDao.findOne(pno);
        String newname=old.getPname();
        String newnote=old.getPnote();
        double newprice=old.getPprice();
        if(pname!=null&&!pname.isEmpty())
            newname=pname;
        if(pnote!=null&&!pnote.isEmpty())
            newnote=pnote;
        if(pprice!=-1)
            newprice=pprice;
        productDao.updateOne(newname,newnote,newprice,pno);
    }
}
