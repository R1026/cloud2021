package com.dx.simple.notice_server.service.impl;

import org.junit.Test;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/8
 */
@Service
public class NoticeServiceImpl {

    @Autowired
    private RedissonClient client;

    @Test
    public void test(){
        System.out.println(client);
        RRemoteService remoteService = client.getRemoteService();
        RBlockingDeque<String> blockingDeque = client.getBlockingDeque("NOTICE");
        blockingDeque.pushAsync("HELLO.THIS IS REDISSON");
        System.out.println(remoteService);
    }

    public void test2() {
        try {
            RBlockingDeque<String> blockingDeque = client.getBlockingDeque("NOTICE");
            String take = blockingDeque.take();
            System.out.println(take);
        }catch (Exception e){

        }
    }
}
