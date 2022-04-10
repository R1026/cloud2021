package com.dx.simple.notice_server.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/9
 */
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonConfig implements Condition {

    private String host;
    private String port;


    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        /*String host = conditionContext.getEnvironment().getProperty("spring.redis.host");
        String port = conditionContext.getEnvironment().getProperty("spring.redis.port");*/
        host = conditionContext.getEnvironment().getProperty("spring.redis.host");
        port = conditionContext.getEnvironment().getProperty("spring.redis.port");
        if (StringUtils.isEmpty(host) && StringUtils.isEmpty(port))
            return false;
        log.info("redis://{}:{}",host,port);
        return true;
    }
}
