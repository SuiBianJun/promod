package com.dlg.projectmodule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {// 跨域访问处理
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }


}

/**
 *
 * A网站访问B网站的接口，静态资源或者其他资源产生的跨域访问，需要B网站在响应头中加入允许跨域请求的头部信息，浏览器才能正常获取数据
 *
 */
