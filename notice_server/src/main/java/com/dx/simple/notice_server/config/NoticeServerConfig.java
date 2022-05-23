package com.dx.simple.notice_server.config;

import com.dx.simple.common_base.pojo.MsgNotice;
import com.dx.simple.notice_server.strategy.SimpleNotice;
import com.dx.simple.notice_server.strategy.SimpleNoticeContext;
import com.dx.simple.notice_server.strategy.SmsNotice;
import com.dx.simple.notice_server.strategy.WechatNotice;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/17
 */
@Slf4j
@Component
public class NoticeServerConfig {

    @Autowired(required = false)
    private RedissonClient client;
    @Autowired
    private SmsNotice smsNotice;
    @Autowired
    private WechatNotice wechatNotice;
    @Value("${com.dx.notice.phone}")
    private String phone;
    @Value("${com.dx.notice.token}")
    private String token;


    public void startNotice() {
        try {
            while (true){
                RBlockingDeque<MsgNotice> blockingDeque = client.getBlockingDeque("NOTICE");
                MsgNotice take = blockingDeque.poll(15, TimeUnit.SECONDS);
                if (null == take){
                    continue;
                }
                //System.out.println(take);
                log.info("=====>队列任务：[{}]",take);
                if (!StringUtils.isEmpty(phone)){
                    SimpleNoticeContext smsContext = new SimpleNoticeContext(smsNotice);
                    smsContext.excute(phone,take);
                }
                if (!StringUtils.isEmpty(token)){
                    SimpleNoticeContext wechatContext = new SimpleNoticeContext(wechatNotice);
                    wechatContext.excute(token,take);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
