package com.planitary.service.md.home;

import com.planitary.entity.model.dto.HomeInfoDto;

/**
 * @author zane
 * @date 2024-10-12 10:27:17
 * 首页内容展示
 */
public interface MDHomeInfoService {
    /**
     * 通过userId获取信息聚合展示到首页
     * @param userId            用户id
     * @return      HomeInfoDto
     */
    HomeInfoDto getHomeInfoByUserId(String userId);
}
