package com.dx.simple.notice_server.listener;

import com.dx.simple.notice_server.config.NoticeServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/17
 */
@Slf4j
@Component
public class NoticeListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NoticeServerConfig noticeServerConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null == event.getApplicationContext().getParent()){
            //System.out.println("启动通知服务......");
            log.info("启动通知服务......");
            noticeServerConfig.startNotice();
        }

    }
}
