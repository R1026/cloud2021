package com.dx.simple.notice_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "com.dx.sms")
public class SmsConfig {

    /**
     * 腾讯云账号密钥对secreId
     */
    private String secretId;
    /**
     * 腾讯云密钥对secretKey
     */
    private String secretKey;
    /**
     * 短信应用appid
     */
    private String appId;
    /**
     * 短信应用appkey
     */
    private String appKey;
    /**
     * 短信签名
     */
    private String signName;


}
