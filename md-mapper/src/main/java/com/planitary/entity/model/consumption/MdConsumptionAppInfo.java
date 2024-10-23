package com.planitary.entity.model.consumption;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zane
 * @date 2024-10-12 10:33:38
 * 消费App详情
 */
@Data
@TableName("md_con_app_info")
public class MdConsumptionAppInfo {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String appId;
    private String appName;
    /**
     * 当前app的消费id，唯一标识一条消费记录
     */
    private String consumptionId;
    private String appIcon;
    /**
     * 消费价格
     */
    private BigDecimal consumptionPrice;
    /**
     * 消费时间
     */
    private String consumptionDate;
    /**
     * 消费类型
     */
    private String consumptionType;
    /**
     * app类型
     */
    private String appType;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 总收入
     */
    private BigDecimal totalIncome;
    /**
     * 用户name
     */
    private String userName;
    @TableLogic
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
