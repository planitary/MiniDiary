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
    CONSUMPTION("消费内容","Consumption"),

    // 记账大类
    INCOME("收入","1"),
    EXPENSE("支出","0"),

    // 记账小类
    SALARY("工资","SALARY"),
    TRANSFER_ACCOUNTS("转账","TRANSFER_ACCOUNTS"),
    REMITTANCE("汇款","REMITTANCE"),
    DIGITAL_TRANSACTIONS("数字交易","DIGITAL_TRANSACTIONS");


    private final String bizType;
    private final String bizCode;

    BizEnum(String bizType,String bizCode){
        this.bizType = bizType;
        this.bizCode = bizCode;
    }
}
