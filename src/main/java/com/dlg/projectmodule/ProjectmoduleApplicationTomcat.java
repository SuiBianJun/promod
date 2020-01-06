package com.dlg.projectmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling// 开启定时任务注解
public class ProjectmoduleApplicationTomcat extends SpringBootServletInitializer {// 1、打包成tomcat直接运行的项目需要修改

    public static void main(String[] args) {
        SpringApplication.run(ProjectmoduleApplicationTomcat.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(ProjectmoduleApplicationTomcat.class);
    }
}
