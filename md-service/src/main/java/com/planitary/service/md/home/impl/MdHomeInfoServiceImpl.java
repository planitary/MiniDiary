package com.planitary.service.md.home.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.planitary.base.commonEnum.BizEnum;
import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.core.exception.MDException;
import com.planitary.entity.mapper.consumption.MdConsumptionMapper;
import com.planitary.entity.mapper.subscription.MdSubscriptionMapper;
import com.planitary.entity.model.consumption.MdConsumptionAppInfo;
import com.planitary.entity.model.dto.HomeInfoDto;
import com.planitary.entity.model.subscription.MdSubscriptionAppInfo;
import com.planitary.service.md.home.MDHomeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    public HomeInfoDto getHomeInfoByUserId(String userId) {
        if (userId == null) {
            log.error("userId为空");
            MDException.exceptionCast("userID为空", ExceptionEnum.PARAMETER_ERROR);
        }
        LambdaQueryWrapper<MdConsumptionAppInfo> mdComAppInfoWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<MdSubscriptionAppInfo> mdSubAppInfoWrapper = new LambdaQueryWrapper<>();

        // 先查找消费组
        mdComAppInfoWrapper.eq(MdConsumptionAppInfo::getUserId,userId);
        MdConsumptionAppInfo mdConsumptionAppInfo = mdConsumptionMapper.selectOne(mdComAppInfoWrapper);
        if (mdConsumptionAppInfo == null){
            log.info("未找到任何结果");
            MDException.exceptionCast(ExceptionEnum.OBJECT_NULL);
        }
        // 在查找订阅组
        mdSubAppInfoWrapper.eq(MdSubscriptionAppInfo::getSubscriptionUserId,userId);
        MdSubscriptionAppInfo mdSubscriptionAppInfo = mdSubscriptionMapper.selectOne(mdSubAppInfoWrapper);
        if (mdSubscriptionAppInfo == null){
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

        return homeInfoDto;
    }
}
