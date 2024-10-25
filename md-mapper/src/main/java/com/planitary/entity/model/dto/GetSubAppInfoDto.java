package com.planitary.entity.model.dto;

import lombok.Data;

/**
 * @author zane
 * @date 2024-10-11 13:54:32
 * 订阅信息的查找dto
 */
@Data
public class GetSubAppInfoDto {
    private String userId;
    private String subscriptionId;
    private String accountingType;
    private String userName;
    private String appId;

}
