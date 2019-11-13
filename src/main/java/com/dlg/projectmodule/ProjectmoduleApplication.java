package com.dlg.projectmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling// 开启定时任务注解
public class ProjectmoduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectmoduleApplication.class, args);
    }

}
