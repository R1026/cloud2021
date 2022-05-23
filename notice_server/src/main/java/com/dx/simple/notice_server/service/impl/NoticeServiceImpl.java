package com.dx.simple.notice_server.service.impl;

import com.dx.simple.common_base.pojo.MsgNotice;
import org.junit.Test;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/8
 */
@Service
public class NoticeServiceImpl {

    @Autowired(required = false)
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
            /*RBlockingDeque<String> blockingDeque = client.getBlockingDeque("NOTICE");
            String take = blockingDeque.take();*/
            while (true){
                RBlockingDeque<MsgNotice> blockingDeque = client.getBlockingDeque("NOTICE");
                MsgNotice take = blockingDeque.poll(15, TimeUnit.SECONDS);
                if (null == take){
                    continue;
                }
                System.out.println(take);
            }
            //MsgNotice take = blockingDeque.take();
        }catch (Exception e){

        }
    }

    public void pushData(MsgNotice msgNotice){
        RBlockingDeque<MsgNotice> blockingDeque = client.getBlockingDeque("NOTICE");
        blockingDeque.pushAsync(msgNotice);

    }

}
