package com.dx.simple.notice_server.utils;

import com.dx.simple.notice_server.config.SmsConfig;
import com.dx.simple.notice_server.enums.TemplateEnum;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/17
 */
@Slf4j
@Component
public class SmsUtil {

    @Autowired
    private SmsConfig smsConfig;

    /**
     * 短信发送
     * @param phone -- 手机号
     * @param templateParamSet -- 模板参数
     * @param templateEnum -- 模板选择
     * @return
     * @throws TencentCloudSDKException
     */
    public String send(String[] phone,String[] templateParamSet,TemplateEnum templateEnum) throws TencentCloudSDKException {
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
        // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
        Credential cred = new Credential(smsConfig.getSecretId(), smsConfig.getSecretKey());
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        SendSmsRequest req = new SendSmsRequest();
        //String[] phoneNumberSet1 = {"13058298544","16675351269"};
        //req.setPhoneNumberSet(phoneNumberSet1);
        req.setPhoneNumberSet(phone);

        req.setSmsSdkAppId(smsConfig.getAppId());
        String signName = smsConfig.getSignName();
        String signName_n = new String(signName.getBytes(CharsetUtil.ISO_8859_1), CharsetUtil.UTF_8);
        req.setSignName(signName_n);

        //String templateid = TemplateEnum.transformId(templateEnum.type);
        req.setTemplateId(templateEnum.id);

        //String[] templateParamSet1 = {"2022-04-14", "500", "7800"};
        //req.setTemplateParamSet(templateParamSet1);
        req.setTemplateParamSet(templateParamSet);

        // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
        SendSmsResponse resp = client.SendSms(req);
        // 输出json格式的字符串回包
        String res = SendSmsResponse.toJsonString(resp);
        log.info(res);
        return res;
    }

}
