package com.dx.simple.notice_server.enums;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/4/17
 */
public enum TemplateEnum {

    /**
     * 成功
     */
    TEMPLATE_SUCCESS(1,"1364285"),
    /**
     * 重复
     */
    TEMPLATE_REPEAT(2,"1364286"),
    /**
     * 失败
     */
    TEMPLATE_FAIL(3,"xxx");

    public Integer type;
    public String id;

    TemplateEnum(Integer type, String id) {
        this.type = type;
        this.id = id;
    }

    public static String transformId(Integer type){
        TemplateEnum[] templateEnums = TemplateEnum.values();
        for (TemplateEnum item : templateEnums) {
            if (type == item.type){
                return item.id;
            }
        }
        return null;

    }

}
