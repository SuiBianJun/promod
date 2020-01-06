package com.dlg.projectmodule.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dlg.projectmodule.config.anno.TokenIgnore;
import com.dlg.projectmodule.exception.UserException;
import com.dlg.projectmodule.response.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTTokenInterceptor implements HandlerInterceptor {// 使用JWT做Token验证，jwt中可包含部分用户信息，进行快速查询。并且支持token 验证
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
            response.setContentType("application/json;charset=utf-8");// 设置返回内容的数据类型
            response.getWriter().print(JSON.toJSONString(new Response<>(301, "token为空")));// 返回json数据
            return false;
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            System.out.println("userId: " + userId);
        } catch (JWTDecodeException j) {
            throw new UserException("token无效");
        }
        if (!("1001".equals(userId))) {
            throw new UserException("token无效");
        }
        // 验证 token 有效性 // 需要使用用户的password 来验证， 防止被篡改
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new UserException("token无效");
        }
        return true;
    }
}
