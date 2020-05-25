package com.xuan.controller;

import com.xuan.bean.PageBean;
import com.xuan.bean.Product;
import com.xuan.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@Api(tags = "ProductController", description = "商品相关")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    Logger logger= LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value = "/pageQuery", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("商品页面请求")
    public PageBean<Product> pageQuery(@RequestParam(value = "currentPage",required = false) String currentPageStr,
                                       @RequestParam(value = "pageSize",required = false) String pageSizeStr,
                                       @RequestParam(value = "pname",required = false) String pname){

        if(!(pname!=null&&pname!="null"&&pname.length()>0))
            pname="";
        if(pname==null||pname.equals("null")||pname.length()<=0)
            pname="";
//        logger.debug(pname.equals("null"));
//        logger.debug(pname.getClass().toString());
//        logger.debug("获得的Pname是---"+pname+"----页码是"+currentPageStr);
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() >0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

        PageBean<Product> pb = productService.pageQuery(currentPage, pageSize,pname);
        logger.debug("商品页面查询成功");
        return pb;
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据ID寻找商品")
    public Product findOne (int pno,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product reProduct=productService.findOne(pno);
        return reProduct;
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("管理员查看商品")
    public List<Product> findAll (HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("管理员查看商品");
        List<Product> products=productService.findAll();
        return products;
    }

    @RequestMapping(value = "/deleteOnePro", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("管理员删除商品")
    public void deleteOnePro(int pno,HttpServletRequest request, HttpServletResponse response) throws IOException{
        productService.deleteOnePro(pno);
    }


}
