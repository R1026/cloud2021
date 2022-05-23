package com.dx.simple.notice_server.strategy;

import com.dx.simple.common_base.pojo.MsgNotice;
import com.dx.simple.notice_server.enums.TemplateEnum;
import com.dx.simple.notice_server.utils.SmsUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/8
 */
@Component
public class SmsNotice implements SimpleNotice {

    @Autowired
    private SmsUtil smsUtil;

    public void msg_notice(String var1,MsgNotice msgNotice) {

        try {
            String[] phone = new String[]{var1};
            LocalDateTime time = msgNotice.getTime();
            String day = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(time);
            if ("0".equals(msgNotice.getAllNums()) && msgNotice.isSuccess()){
                String[] templateParamSet = new String[]{day};
                smsUtil.send(phone,templateParamSet, TemplateEnum.TEMPLATE_REPEAT);
            }else if (msgNotice.isSuccess()){
                String[] templateParamSet = new String[]{day,msgNotice.getAddNums(),msgNotice.getAllNums()};
                smsUtil.send(phone,templateParamSet,TemplateEnum.TEMPLATE_SUCCESS);
            }
        }catch (Exception e){

        }

    }



}
