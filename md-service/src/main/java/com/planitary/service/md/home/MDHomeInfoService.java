package com.planitary.service.md.home;

import com.planitary.entity.model.dto.AddBillBaseDTO;
import com.planitary.entity.model.dto.GetAppInfo;
import com.planitary.entity.model.dto.GetSubAppInfoDto;
import com.planitary.entity.model.dto.HomeInfoDto;
import com.planitary.entity.model.income.IncomeAppInfo;

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
     * 通过userId查询用户收入信息
     * @param getAppInfo
     * @return
     */
    IncomeAppInfo getIncomeInfoByRecordId(GetAppInfo getAppInfo);

    /**
     * 新增记账
     * @param addBillBaseDTO            记账dto
     * @return
     */
    String addBill(AddBillBaseDTO addBillBaseDTO);
}
