package com.planitary.entity.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.planitary.base.handler.CustomBigDecimalSerializer;
import lombok.Data;

import javax.sql.rowset.serial.SerialStruct;
import java.math.BigDecimal;

/**
 * @author zane
 * @date 2024-10-23 17:32:21
 * 消费App基本信息表，一个app在一个周期内有多条记录，列表页需要根据appName聚合展示
 */
@Data
public class ConsumptionAppBaseInfo {
    private String appId;
    private String appIcon;
    private String appName;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal totalCost;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal totalIncome;
    /**
     * app中文名称
     */
    private String appNameDesc;
}
