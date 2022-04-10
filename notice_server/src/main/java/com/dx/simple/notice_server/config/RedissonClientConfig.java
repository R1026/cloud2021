package com.dx.simple.notice_server.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/8
 */
@Configuration
@Conditional(RedissonConfig.class)
public class RedissonClientConfig {

    @Autowired
    private RedissonConfig redissonConfig;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redissonConfig.getHost() + ":" + redissonConfig.getPort());
        RedissonClient client = Redisson.create(config);
        return client;
    }

}
