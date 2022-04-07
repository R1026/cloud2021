package com.dx.simple.nacos_provider.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/1
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/api_1")
    public void api_1(@RequestParam(required = false) String name){
        System.out.println("======>" + name);
    }

}
