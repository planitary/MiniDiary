package com.planitary.entity.model.dto;

import com.planitary.entity.model.consumption.MdConsumptionAppInfo;
import com.planitary.entity.model.subscription.MdSubscriptionAppInfo;
import lombok.Data;

/**
 * @author zane
 * @date 2024-11-13 17:35:49
 */
@Data
public class AddBillBaseDTO {
    private String billType;
    private String accountType;
    private String accountingTime;
    private String accountingType;
    private String amount;
    private String bank;
    private String merchant;
    private String remark;
    private String sourceApp;
    private String userId;
}
