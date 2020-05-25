package com.xuan.controller;

import com.xuan.service.ProductService;
import com.xuan.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@Api(tags = "UploadController", description = "增加、修改商品相关")
@RequestMapping("/upload")
public class UploadController {
    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/uploadpic", method = RequestMethod.POST)
    @ApiOperation("上传文件请求")
    public String uploadpic(String pname,
                          String pnote,
                          double pprice,
                          @RequestParam("proimg") MultipartFile file,
                          HttpServletRequest request,
                          HttpServletResponse response) throws FileUploadException, IOException {
        logger.debug("文件上传 "+pname+" "+pnote+" "+pprice);
        int lastNo=productService.addOne(pname,pnote,pprice);
        logger.debug("编号是 "+lastNo);
        String filename="p"+lastNo+".jpg";
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/proImg/";
        System.out.println(filePath);
        File dest = new File(filePath + filename);
        try {
            file.transferTo(dest);
             logger.info("上传成功");
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return "redirect:/product_manage.html";
    }

    @RequestMapping(value = "/changepro", method = RequestMethod.POST)
    @ApiOperation("修改文件请求")
    public String changepro(int pno,
                          String pname,
                          String pnote,
                          double pprice,
                          @RequestParam("proimg") MultipartFile file,
                          HttpServletRequest request,
                          HttpServletResponse response) throws FileUploadException, IOException {
        logger.debug("替换文件上传 "+pname+" "+pnote+" "+pprice+"----"+pno);
        productService.updateOne(pname,pnote,pprice,pno);

        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/proImg/";
        logger.debug(filePath);
        if(StringUtils.isNotBlank(file.getOriginalFilename())){
            logger.debug("有文件");
            String filename="p"+pno+".jpg";
            File dest = new File(filePath + filename);
            try {
                file.transferTo(dest);
                logger.info("上传成功");
            } catch (IOException e) {
                logger.error(e.toString(), e);
            }
        }else {
            logger.debug("没有文件");
        }

        return "redirect:/product_manage.html";
    }


}
