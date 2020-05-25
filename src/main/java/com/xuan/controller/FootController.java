package com.xuan.controller;

import com.xuan.bean.FootBean;
import com.xuan.service.FootService;
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
import java.util.List;

@Controller
@Api(tags = "FootController", description = "足迹相关")
@RequestMapping("/foot")
public class FootController {
    @Autowired
    FootService footService;
    Logger logger= LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/addOne", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加足迹请求")
    public void addOne(String uname,
                       int pno,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException{
        logger.debug("足迹+1");
        footService.addOne(uname,pno);
    }
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("添加足迹请求")
    public List<FootBean> findAll(){
        return footService.findAll();
    }

}
