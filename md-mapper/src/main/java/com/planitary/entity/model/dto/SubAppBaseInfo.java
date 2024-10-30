package com.planitary.entity.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zane
 * @date 2024-10-23 17:24:16
 * 订阅app基本信息，一个app的订阅消息在一个周期内只有一条数据
 */
@Data
public class SubAppBaseInfo {
    String appId;
    String appIcon;
    BigDecimal subscriptionPrice;
    String appName;
    String subscriptionDate;
    /**
     * app中文名称
     */
    private String appNameDesc;
}
