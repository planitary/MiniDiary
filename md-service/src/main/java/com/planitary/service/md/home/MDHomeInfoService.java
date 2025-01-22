package com.planitary.service.md.home;

import com.planitary.entity.model.dto.*;
import com.planitary.entity.model.income.IncomeAppInfo;

import java.util.List;
import java.util.Map;

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
    HomeInfoDto getHomeInfoByUserId(GetAppInfo getAppInfo);

    /**
     * 通过recordId查询单笔收入信息（内部调用）
     * @param getAppInfo
     * @return
     */
    IncomeAppInfo getIncomeInfoByRecordId(String recordId);

    // 临时测试，展示聚合结果
    List<IncomeBaseAppInfo> getIncomeInfoByUserId(String userId);

    /**
     * 新增记账
     * @param addBillBaseDTO            记账dto
     * @return
     */

}
