package com.planitary.service.md.subscription;

import com.planitary.entity.model.subscription.MdSubscriptionAppInfo;

/**
 * @author zane
 * @date 2024-10-11 10:05:58
 */
public interface MdSubscriptionAppService {
    /**
     * 获取当前用户订阅的所有app信息
     * @param userId
     * @return
     */
    MdSubscriptionAppInfo getSubAppInfo(String userId);
}
