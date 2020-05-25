package com.xuan.controller;

import com.google.gson.Gson;
import com.xuan.bean.*;
import com.xuan.service.CarService;
import com.xuan.service.OderService;
import com.xuan.service.ProductService;
import com.xuan.service.impl.ProductServiceImpl;
import com.xuan.util.MailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@Api(tags = "CarController", description = "购物车相关")
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    ProductService productService;
    @Autowired
    OderService oderService;
    Logger logger= LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/addCar",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("加入购物车")
    public void addCar(int pno,HttpServletRequest request, HttpServletResponse response) throws IOException {
        User nowUser=(User) request.getSession().getAttribute("user");
        String username=nowUser.getUsername();
        carService.addCar(username,pno);

        logger.debug(username+"加入购物车"+pno);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查看购物车")
    //查看购物车全部内容
    public CarBean findAll(HttpServletRequest request,
                           HttpServletResponse response) throws IOException{
        User nowUser=(User) request.getSession().getAttribute("user");
        String username=nowUser.getUsername();
        logger.debug("查看购物车"+username);
        //得到的即是购物车对象
        CarBean carBean=carService.findAll(username);
        logger.debug(carBean.toString());
        return carBean;
    }

    @RequestMapping(value = "/subOne",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("减少商品")
    public void subOne(int pno,
                       HttpServletRequest request,
                       HttpServletResponse response){
        User nowUser=(User) request.getSession().getAttribute("user");
        String username=nowUser.getUsername();
        carService.subOne(username,pno);
        logger.debug("减少商品");
    }

    @RequestMapping(value = "/delectOne",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("删除商品")
    public void delectOne(int pno,
                          HttpServletRequest request,
                          HttpServletResponse response){
        User nowUser=(User) request.getSession().getAttribute("user");
        String username=nowUser.getUsername();
        carService.delectOne(username,pno);
        logger.debug("删除商品");
    }

    @RequestMapping(value = "/confirmOrder",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("确认商品订单")
    public void confirmOrder(String email,String addre,
                             HttpServletRequest request,
                             HttpServletResponse response){
        User nowUser=(User) request.getSession().getAttribute("user");
        String username=nowUser.getUsername();
        String name=nowUser.getName();
        //得到购物车内容并加入邮件
        //购物车对象
        CarBean carBean=carService.findAll(username);
        //购物车内容
        List<CarItem> carItemList=carBean.getCarObject();
        //存储具体商品信息以便加入订单表
        List<OderItem> orderPro=new LinkedList<>();
        //根据商品编号得到商品名称
        StringBuilder emailText=new StringBuilder("");
        double sumPrice=0;
        emailText.append("亲爱的："+name+":");
        emailText.append("<br>");
        emailText.append("已经收到您的订单");
        emailText.append("<br>");
        emailText.append("收货地址："+addre);
        emailText.append("<br>");
        emailText.append("订单详情:");
        emailText.append("<br>");
        emailText.append("<table border=\"1\" cellspacing=\"0\">\n    <tr>\n    <td>名称</td>\n    <td>数量</td>\n  <td>单价</td> <td>总价</td> </tr>\n ");
        for (CarItem carItem : carItemList) {
            Product itemProduct=productService.findOne(carItem.getPno());
            String pname=itemProduct.getPname();
            double pprice=itemProduct.getPprice();
            long pnums=carItem.getNums();
            //处理订单项，准备加入订单表
            OderItem tempOder=new OderItem();
            tempOder.setProduct(itemProduct);
            tempOder.setNum((int) pnums);
            orderPro.add(tempOder);

            //logger.debug(pname+"  数量： "+pnums)
            emailText.append("<tr><td>"+pname+"</td><td>"+pnums+"</td><td>"+pprice+"</td>"+"<td>"+pnums*pprice+"</td>");
            sumPrice+=pnums*pprice;
            //删除购物车项目
            carService.delectOne(username,carItem.getPno());
        }
        emailText.append("</table>");
        emailText.append("<h3>总计："+sumPrice+"</h3>");
        String text=emailText.toString();
        logger.debug(text);
        MailUtils.sendMail(email,text,"下单详情");

        //添加进数据库订单管理
        Date da=new Date();
        logger.debug(da.toString());
        SimpleDateFormat SFDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strda=SFDate.format(da);
        /*
        logger.debug("用户名"+username);
        logger.debug("邮箱号"+email);
        logger.debug("地址"+addre);
        logger.debug("内容"+writeValueAsString(orderPro));
        logger.debug("总金额"+sumPrice);
        logger.debug("日期"+strda);*/
        Gson gson = new Gson();
        String orderProStr = gson.toJson(orderPro);
        logger.debug("对象转json "+orderProStr);
        oderService.addOne(username,email,addre,orderProStr,sumPrice,strda);
    }

    @RequestMapping(value = "/findAllOrder",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("管理员查看商品订单")
    public List<OrderDetail> findAllOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<OrderDetail> orders=oderService.findAll();
        return orders;
    }

}
