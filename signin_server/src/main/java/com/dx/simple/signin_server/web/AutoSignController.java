package com.dx.simple.signin_server.web;

import com.dx.simple.signin_server.service.IAutoSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/11
 */
@RestController
@RequestMapping("/autoSign")
public class AutoSignController {

    @Autowired
    private IAutoSignService service;

    @GetMapping("/signIn")
    public Map signIn(){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            service.signIn();
            map.put("times",System.currentTimeMillis());
            map.put("success",true);
            map.put("msg","操作成功！");
        } catch (Exception e){
            e.printStackTrace();
            map.put("times",System.currentTimeMillis());
            map.put("success",false);
            map.put("errmsg",e.getMessage());
        }
        return map;
    }

}
