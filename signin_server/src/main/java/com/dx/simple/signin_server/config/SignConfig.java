package com.dx.simple.signin_server.config;

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
@ConfigurationProperties(prefix = "com.dx.sign")
public class SignConfig {

    private String baseurl;
    private String cookie;
    private String aid;
    private String uuid;
    private String signature;

}
