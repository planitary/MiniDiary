package com.planitary.entity.model.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zane
 * @date 2024-11-13 17:58:25
 * 通用app（收入类型，非订阅等类型）
 */
@Data
public class CommonAppBase {
    private String appId;
    private String appName;
    private String appIcon;
    private String userId;
    private BigDecimal incomeAmount;
    private String incomeDate;
    private String billType;
}
