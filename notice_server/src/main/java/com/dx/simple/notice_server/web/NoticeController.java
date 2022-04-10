package com.dx.simple.notice_server.web;

import com.dx.simple.notice_server.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/9
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeServiceImpl service;

    @GetMapping("/test")
    public void test(){
        service.test();
    }

    @GetMapping("/test2")
    public void test2(){
        service.test2();
    }

}
