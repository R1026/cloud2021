package com.dx.simple.notice_server.web;

import com.dx.simple.notice_server.enums.TemplateEnum;
import com.dx.simple.notice_server.service.impl.NoticeServiceImpl;
import com.dx.simple.notice_server.utils.SmsUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.junit.Test;
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
    @Autowired
    private SmsUtil smsUtil;

    @GetMapping("/test")
    public void test(){
        service.test();
    }

    @GetMapping("/test2")
    public void test2(){
        service.test2();
    }

    @GetMapping("/test3")
    public void testsend() throws Exception{


        String[] phoneNumberSet1 = {"16675351269","13058298544"};
        String[] templateParamSet1 = {"2022-04-17", "600", "8400"};
        String tes = smsUtil.send(phoneNumberSet1, templateParamSet1, TemplateEnum.TEMPLATE_SUCCESS);
    }

}
