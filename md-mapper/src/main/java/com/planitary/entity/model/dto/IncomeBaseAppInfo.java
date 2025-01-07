package com.planitary.entity.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.planitary.base.handler.CustomBigDecimalSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zane
 * @date 2025-01-07 19:24:42
 * 聚合展示收入
 */
@Data
public class IncomeBaseAppInfo {
    private String appId;
    private String appName;
    private String appIcon;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal totalIncome;
    private String incomeType;
}
