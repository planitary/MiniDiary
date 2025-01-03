package com.planitary.entity.model.income;

import com.baomidou.mybatisplus.annotation.*;
import com.planitary.entity.model.common.CommonAppBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zane
 * @date 2024-12-16 17:02:38
 */
@EqualsAndHashCode(callSuper = true)
@TableName("md_income_app_info")
@Data
public class IncomeAppInfo extends CommonAppBase implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String incomeRecordId;
    private String incomeDate;
    private BigDecimal incomePrice;
    private String incomeType;
    @TableLogic
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;



}
