package com.xuan.filter;

import com.xuan.bean.User;
import com.xuan.service.UserService;
import org.assertj.core.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service(value = "AutoLoginIntercepter")
public class AutoLoginIntercepter implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("拦截前");
        Cookie[] cookies=request.getCookies();
        String username=null;
        String password=null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }
        if(username!=null&&password!=null){
            User cookieUser=new User();
            cookieUser.setUsername(username);
            cookieUser.setPassword(password);
            User resltUser=userService.login(cookieUser);
            if(resltUser!=null){
                request.getSession().setAttribute("user",resltUser);
                System.out.println("自动登录重定向！"+resltUser);
                response.sendRedirect("login_ok.html");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //System.out.println("拦截后");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
       // System.out.println("渲染后");
    }

}