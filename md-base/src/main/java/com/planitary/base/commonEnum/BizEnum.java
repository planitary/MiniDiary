package com.planitary.base.commonEnum;

import lombok.Getter;

/**
 * @author zane
 * @date 2024-10-18 18:09:33
 * @description   业务内筒
 */
@Getter
public enum BizEnum {
    SUBSCRIPTION("订阅内容","Subscription"),
    CONSUMPTION("消费内容","Consumption");


    private final String bizType;
    private final String bizCode;

    BizEnum(String bizType,String bizCode){
        this.bizType = bizType;
        this.bizCode = bizCode;
    }
}
