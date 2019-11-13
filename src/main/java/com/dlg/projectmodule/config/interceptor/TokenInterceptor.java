package com.dlg.projectmodule.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.dlg.projectmodule.config.anno.TokenIgnore;
import com.dlg.projectmodule.response.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {// 拦截器做token 检查

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("start token check");

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;


        if(method.hasMethodAnnotation(TokenIgnore.class)){// 存在TokenIgnore注解，不用检查Token
            return true;
        }

        // token 检查
        String token = request.getHeader("accesstoken");// 获取token

        if("".equals(token) || null == token){
            // token不存在，需要先登录
            // throw new UserException("token为空");
            // response.sendRedirect('/index.html');// 浏览器重定向
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(new Response<>(301, "token为空")));// 返回json数据
        }

        // 检查token 是否过期
        // 如果过期，重新登录

        // 如果没有过期，允许访问，并更新token 有效期

        return true;
    }
}
