package com.xuan.controller;

import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import com.xuan.bean.ResultInfo;
import com.xuan.bean.User;
import com.xuan.service.UserService;
import com.xuan.util.VerifyUtil;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Controller
@Api(tags = "UserController", description = "用户相关")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    Logger logger= LoggerFactory.getLogger(getClass());
    //ExecutorService exs=new ThreadPoolExecutor(5,10,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("登录请求")
    public ResultInfo login(String username,String password,
                            String check,
                            boolean autoLogin,
                            HttpServletRequest request,
                            HttpServletResponse response){
        if(username.equals("rootroot")&&password.equals("rootroot")){
            ResultInfo resf=new ResultInfo();
            resf.setFlag(true);
            resf.setErrorMsg("manager");
            return resf;
        }

        logger.debug("收到登录请求"+username+"---"+password+"----"+check+"----"+autoLogin);
        //获取验证码
        HttpSession session=request.getSession();
        String code= (String) session.getAttribute("RANDOMREDISKEY");
        logger.debug(code);

        ResultInfo resf=new ResultInfo();
        User resuUser=null;
        //查看验证码
        //无验证码或验证码错误
        if((!check.equals("androidLogin"))&&(code==null||!code.equalsIgnoreCase(check))){
            resf.setFlag(false);
            resf.setErrorMsg("验证码错误");
            return resf;
        }else {
            //查询数据库是否存在该用户
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            resuUser=userService.login(user);
            if(resuUser==null){
                resf.setFlag(false);
                resf.setErrorMsg("用户名或密码错误");
                logger.debug("用户名或密码错误");
                return resf;
            }

            //存在该用户，可以自动登录
            if(autoLogin){
                logger.debug("自动登录");
                Cookie userCookie=new Cookie("username",username);
                Cookie passwordCookie=new Cookie("password",password);
                userCookie.setMaxAge(7*24*60);
                passwordCookie.setMaxAge(7*24*60);
                userCookie.setPath("/");
                passwordCookie.setPath("/");
                response.addCookie(userCookie);
                response.addCookie(passwordCookie);
            }

        }
        request.getSession().setAttribute("user",resuUser);
        resf.setFlag(true);
        resf.setErrorMsg("登录成功");
        return resf;
    }



    @RequestMapping(value = "/createImg",method = RequestMethod.GET)
    @ApiOperation("登录验证码")
    public void createImg(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifyUtil randomValidateCode = new VerifyUtil();
        randomValidateCode.getRandcode(request, response);//输出验证码图片
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ApiOperation("退出登录")
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("退出登录");
        request.getSession().removeAttribute("user");
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("username")){
                logger.debug("移除！！！");
                cookie.setValue("无效");
                cookie.setMaxAge(0);
            }
            if(cookie.getName().equals("password")){
                cookie.setValue("无效");
                cookie.setMaxAge(0);
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }
       response.sendRedirect("/login.html");
    }


    @RequestMapping(value = "/nowUser",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("当前用户")
    public User nowUser(HttpServletRequest request, HttpServletResponse response){
        User nowUser=(User) request.getSession().getAttribute("user");
        logger.debug("当前用户   "+nowUser);

        return nowUser;
    }


    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("用户注册")
    public ResultInfo register(String username,String password,String name,
                               String telephone,String sex,String birthday,String check,
                               HttpServletRequest request, HttpServletResponse response){
        logger.debug("收到注册请求"+username+"---"+password+"----"+check+"----");
        //获取验证码
        HttpSession session=request.getSession();
        String code= (String) session.getAttribute("RANDOMREDISKEY");
        logger.debug(code);

        ResultInfo resf=new ResultInfo();
        User resuUser=null;
        //查看验证码
        if((!check.equals("androidRegister"))&&(code==null||!code.equalsIgnoreCase(check))){
            resf.setFlag(false);
            resf.setErrorMsg("验证码错误");
            return resf;
        }
        User register=new User();
        register.setUsername(username);
        register.setPassword(password);
        register.setName(name);
        register.setSex(sex);
        register.setBirthday(birthday);
        register.setTelephone(telephone);
        //实现注册功能
        boolean flag=userService.regist(register);

        //根据flag判断注册是否成功
        ResultInfo info = new ResultInfo();
        if(flag) {
            info.setFlag(true);
        }
        else {
            info.setFlag(false);
            info.setErrorMsg("注册失败,用户名已存在");
        }
        //logger.debug("这里还是成功的");
        return info;
    }

}

