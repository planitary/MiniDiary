package com.planitary.entity.model.dto;

import com.planitary.entity.model.subscription.MdSubscriptionAppInfo;
import lombok.Data;

import javax.servlet.http.PushBuilder;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zane
 * @date 2024-10-12 10:14:13
 * 首页记账信息DTO
 */
@Data
public class HomeInfoDto {
    /**
     * 记账类型-订阅项目、消费项目
     */
    private List<String> accountingType;
    /**
     * 净收入、总订阅消费额
     */
    private BigDecimal totalAmounts;
    /**
     * 总支出
     */
    private BigDecimal totalCost;
    private String currentYear;
    private String currentMonth;
    private String currentDate;
    /**
     * 订阅App数量
     */
    private Integer subscriptionCount;
    private List<SubAppBaseInfo> mdSubscriptionAppInfos;
    /**
     * 消费App数量
     */
    private Integer consumptionCount;

    /**
     * 消费App详情
     */
    private List<ConsumptionAppBaseInfo> mdConsumptionAppInfos;

}
