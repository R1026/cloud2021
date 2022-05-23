package com.dx.simple.signin_server.tasks;

import com.dx.simple.signin_server.service.IAutoSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/5/21
 */
@Slf4j
@Component
public class AutoSigninTask {

    @Autowired
    private IAutoSignService service;

    @Scheduled(cron = "0 10 9 1/1 * ? ")
    public void signin(){
        service.signIn();
    }

}
