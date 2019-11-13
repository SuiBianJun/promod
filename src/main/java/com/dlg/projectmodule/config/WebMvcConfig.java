package com.dlg.projectmodule.config;

import com.dlg.projectmodule.config.interceptor.JWTTokenInterceptor;
import com.dlg.projectmodule.config.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {// 添加拦截器
        //registry.addInterceptor(new TokenInterceptor())// 普通token验证
        registry.addInterceptor(new JWTTokenInterceptor())// JWT token 验证
                .addPathPatterns("/user/**")
                .excludePathPatterns("/swagger-ui.html/**");
    }
}
