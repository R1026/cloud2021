package com.dx.simple.nacos_consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConsumeApplication.class, args);
	}

}
