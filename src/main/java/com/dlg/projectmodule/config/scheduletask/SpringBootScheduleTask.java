package com.dlg.projectmodule.config.scheduletask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringBootScheduleTask {// springboot 自带的任务调度

    @Scheduled(fixedRate = 3000)// 固定频率执行
    public void task(){
        System.out.println("task run");
    }

}
