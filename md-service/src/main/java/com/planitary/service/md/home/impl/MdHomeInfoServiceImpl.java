package com.planitary.service.md.home.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.planitary.base.commonEnum.BizEnum;
import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.core.exception.MDException;
import com.planitary.entity.mapper.consumption.MdConsumptionMapper;
import com.planitary.entity.mapper.subscription.MdSubscriptionMapper;
import com.planitary.entity.model.consumption.MdConsumptionAppInfo;
import com.planitary.entity.model.dto.GetAppInfo;
import com.planitary.entity.model.dto.GetSubAppInfoDto;
import com.planitary.entity.model.dto.HomeInfoDto;
import com.planitary.entity.model.dto.SubAppBaseInfo;
import com.planitary.entity.model.subscription.MdSubscriptionAppInfo;
import com.planitary.service.md.home.MDHomeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author zane
 * @date 2024-10-12 10:28:28
 */
@Service
@Slf4j
public class MdHomeInfoServiceImpl implements MDHomeInfoService {
    @Autowired
    MdConsumptionMapper mdConsumptionMapper;

    @Autowired
    MdSubscriptionMapper mdSubscriptionMapper;

    @Override
    public HomeInfoDto getHomeInfoByUserId(GetAppInfo getAppInfo) {
        String userId = getAppInfo.getUserId();
        if (userId == null) {
            log.error("userId为空");
            MDException.exceptionCast("userID为空", ExceptionEnum.PARAMETER_ERROR);
        }
        LambdaQueryWrapper<MdConsumptionAppInfo> mdComAppInfoWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<MdSubscriptionAppInfo> mdSubAppInfoWrapper = new LambdaQueryWrapper<>();

        // 先查找消费组
        mdComAppInfoWrapper.eq(MdConsumptionAppInfo::getUserId,userId);
        List<MdConsumptionAppInfo> mdConsumptionAppInfos = mdConsumptionMapper.selectList(mdComAppInfoWrapper);
        if (mdConsumptionAppInfos == null){
            log.info("未找到任何结果");
            MDException.exceptionCast(ExceptionEnum.OBJECT_NULL);
        }
        // 在查找订阅组
        mdSubAppInfoWrapper.eq(MdSubscriptionAppInfo::getSubscriptionUserId,userId);
        List<MdSubscriptionAppInfo> mdSubscriptionAppInfos = mdSubscriptionMapper.selectList(mdSubAppInfoWrapper);
        if (mdSubscriptionAppInfos == null){
            log.info("未找到任何结果");
            MDException.exceptionCast(ExceptionEnum.OBJECT_NULL);
        }

        // 聚合展示DTO
        HomeInfoDto homeInfoDto = new HomeInfoDto();
        List<String> accountingTypeList = new ArrayList<>();
        // 记账类型
        for (BizEnum bizEnum : BizEnum.values()){
            accountingTypeList.add(bizEnum.getBizCode());
        }
        homeInfoDto.setAccountingType(accountingTypeList);
        // TODO: 2024/10/18 剩余DTO
        // 订阅类型：订阅总额,订阅APP价格相加
        if (Objects.equals(getAppInfo.getAccountingType(), BizEnum.SUBSCRIPTION.getBizCode())){
            BigDecimal totalPrice = BigDecimal.ZERO;
            List<SubAppBaseInfo> mdSubscriptionAppInfoList = new ArrayList<>();

            for (MdSubscriptionAppInfo mdSubscriptionAppInfo : mdSubscriptionAppInfos){
                // 订阅app基本信息
                SubAppBaseInfo subAppBaseInfo = new SubAppBaseInfo();
                totalPrice = totalPrice.add(mdSubscriptionAppInfo.getSubscriptionPrice());
                subAppBaseInfo.setAppAmount(mdSubscriptionAppInfo.getSubscriptionPrice());
                subAppBaseInfo.setAppId(mdSubscriptionAppInfo.getAppId());
                subAppBaseInfo.setAppIcon(mdSubscriptionAppInfo.getAppIcon());
                subAppBaseInfo.setAppName(mdSubscriptionAppInfo.getAppName());
                subAppBaseInfo.setSubscriptionTime(mdSubscriptionAppInfo.getSubscriptionDate());
                mdSubscriptionAppInfoList.add(subAppBaseInfo);
            }
            homeInfoDto.setTotalAmounts(totalPrice);
            homeInfoDto.setMdSubscriptionAppInfos(mdSubscriptionAppInfoList);
        }

        //订阅类型：消费总额，消费App相加-收入
        if (Objects.equals(getAppInfo.getAccountingType(),BizEnum.CONSUMPTION.getBizCode())){
            BigDecimal totalCost = BigDecimal.ZERO;
            BigDecimal totalIncome = mdConsumptionAppInfos.get(0).getTotalIncome();
            for (MdConsumptionAppInfo mdConsumptionAppInfo : mdConsumptionAppInfos){
                totalCost = totalCost.add(mdConsumptionAppInfo.getConsumptionPrice());
            }
            // 总消费
            homeInfoDto.setTotalCost(totalCost);
            // 净收入
            BigDecimal totalPrice = totalCost.subtract(totalIncome);
            homeInfoDto.setTotalAmounts(totalPrice);
        }
        homeInfoDto.setSubscriptionCount(mdSubscriptionAppInfos.size());
        return homeInfoDto;
    }
}
