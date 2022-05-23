package com.dx.simple.signin_server.service.impl;

import com.dx.simple.common_base.pojo.MsgNotice;
import com.dx.simple.notice_server.service.impl.NoticeServiceImpl;
import com.dx.simple.signin_server.config.SignConfig;
import com.dx.simple.signin_server.service.IAutoSignService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/11
 */
@Slf4j
@Service
public class AutoSignServiceImpl implements IAutoSignService {

    @Autowired
    private SignConfig signConfig;
    @Autowired
    private Environment environment;
    @Autowired(required = false)
    private RedissonClient client;
    @Autowired
    private NoticeServiceImpl noticeService;

    public void signIn() {
        /*String phone = environment.getProperty("com.dx.notice.phone","");
        String token = environment.getProperty("com.dx.notice.token","");*/

        /*String base_url = environment.getProperty("com.dx.sign.baseurl","");
        String cookie = environment.getProperty("com.dx.sign.cookie","");
        String aid = environment.getProperty("com.dx.sign.aid","");
        String uuid = environment.getProperty("com.dx.sign.uuid","");
        String _signature = environment.getProperty("com.dx.sign._signature","");*/

        if (StringUtils.isEmpty(signConfig.getBaseurl())){
            throw new RuntimeException("请配置掘金签到url");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie",signConfig.getCookie());
        headers.set("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        HttpEntity httpEntity = new HttpEntity(headers);

        Map<String,String> params = new HashMap<String, String>();
        params.put("aid",signConfig.getAid());
        params.put("uuid",signConfig.getUuid());
        params.put("_signature",signConfig.getSignature());
        RestTemplate restTemplate = new RestTemplate();
        String url = signConfig.getBaseurl() + "?aid={aid}&uuid={uuid}&_signature={_signature}";

        Map result_map = restTemplate.postForObject(url, httpEntity, Map.class, params);
        //{err_no=15001, err_msg=您今日已完成签到，请勿重复签到, data=null}
        //{err_no=0, err_msg=success, data={incr_point=600, sum_point=5117}}
        //System.out.println(result_map);
        log.info("=====>result_map:[{}]",result_map);
        MsgNotice msgNotice = null;
        if (result_map.containsKey("err_no") && 0 == Integer.valueOf(result_map.get("err_no").toString())){
            Map<String,Object> signData = (Map)result_map.get("data");
            msgNotice = MsgNotice.builder().success(true).allNums(signData.get("sum_point") + "").addNums(signData.get("incr_point") + "").time(LocalDateTime.now()).build();
        } else if (result_map.containsKey("err_no") && 15001 == Integer.valueOf(result_map.get("err_no").toString())){
            String err_msg = result_map.get("err_msg") + "";
            msgNotice = MsgNotice.builder().success(true).allNums("0").addNums("0").time(LocalDateTime.now()).build();
        }
        if (null != msgNotice){
            noticeService.pushData(msgNotice);
        }
        /*if (!StringUtils.isEmpty(phone)){
            //短信通知
            CompletableFuture.runAsync(() -> {
                noticeService.pushData(MsgNotice.builder().success(true).allNums("500").build());
            });
        }
        if (!StringUtils.isEmpty(token)){
            //微信公众号通知
            CompletableFuture.runAsync(() -> {
                noticeService.pushData(MsgNotice.builder().success(true).allNums("500").build());
            });
        }*/

    }
}
