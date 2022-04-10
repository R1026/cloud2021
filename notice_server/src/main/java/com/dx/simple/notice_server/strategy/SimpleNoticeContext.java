package com.dx.simple.notice_server.strategy;

import com.dx.simple.common_base.pojo.MsgNotice;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/8
 */
public class SimpleNoticeContext {

    private SimpleNotice notice;

    public SimpleNoticeContext(SimpleNotice notice){
        this.notice = notice;
    }

    public void excute(String var1,MsgNotice msgNotice){
        notice.msg_notice(var1,msgNotice);
    }

}
