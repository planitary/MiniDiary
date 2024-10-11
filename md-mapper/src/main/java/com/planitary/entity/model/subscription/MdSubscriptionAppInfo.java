package com.planitary.entity.model.subscription;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zane
 * @date 2024-10-10 17:49:00
 * 订阅APP详情
 */
@Data
@TableName("md_sub_app_info")
public class MdSubscriptionAppInfo implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 订阅App标识
     */
    private String appId;

    /**
     * 订阅App名称
     */
    private String appName;

    /**
     * 订阅id
     */
    private String subscriptionId;

    /**
     * app图标
     */
    private String appIcon;
    /**
     * 订阅价格
     */
    private BigDecimal subscriptionPrice;

    /**
     * 订阅时间
     */
    private String subscriptionDate;

    /**
     * 订阅时长
     */
    private String duration;

    /**
     * 预计到期时间
     */
    private String expirationDate;

    /**
     * app类型
     */
    private String appType;

    /**
     * 订阅人id
     */
    private String subscriptionUserId;

    /**
     * 订阅人name
     */
    private String subscriptionUserName;

    @TableLogic
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

}
