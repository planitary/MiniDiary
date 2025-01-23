package com.planitary.entity.model.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zane
 * @date 2024-11-13 17:58:25
 * 通用app（收入类型，非订阅等类型）
 * 注意，当记账类型为收入时，appType固定为INCOME，appName根据枚举不同而不同
 * accountingType为工资时，appName为记账银行；accountingType不是工资时，正常传App
 */
@Data
public class CommonAppBase {
    private String appId;
    private String appName;
    private String appIcon;
    private String userId;
    private String userName;
    private String appType;
}
