package com.dx.simple.notice_server.strategy;

import com.dx.simple.common_base.pojo.MsgNotice;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/8
 */
@Component
public class WechatNotice implements SimpleNotice {

    public void msg_notice(String var1,MsgNotice msgNotice) {
        String day = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(msgNotice.getTime());
        String push_url = "http://www.pushplus.plus/send";
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> bodyParam = new HashMap<>();
        bodyParam.put("token",var1);
        bodyParam.put("title",day + " 签到结果");

        if (msgNotice.isSuccess() && "0".equals(msgNotice.getAllNums())){
            bodyParam.put("content","您今日(" + day + ")已完成签到，请勿重复签到。");
        }else if (msgNotice.isSuccess()){

            //当日签到成功({1})，经验值+{2}，当前经验：{3}。
            String content = "当日签到成功(%s)，经验值+%s，当前经验：%s。";
            bodyParam.put("content",String.format(content,day,msgNotice.getAddNums(),msgNotice.getAllNums()));
        }

        HttpEntity httpEntity = new HttpEntity(bodyParam);
        String res = restTemplate.postForObject(push_url, httpEntity, String.class);
        System.out.println(res);
    }
}
