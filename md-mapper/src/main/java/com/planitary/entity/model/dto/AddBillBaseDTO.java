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
    private String appType;
    private MdConsumptionAppInfo consumptionAppInfo;
    private MdSubscriptionAppInfo subscriptionAppInfo;
    private String userId;
}
