package com.planitary.service.md.home.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.planitary.base.commonEnum.AppEnum;
import com.planitary.base.commonEnum.BizEnum;
import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.core.exception.MDException;
import com.planitary.entity.mapper.consumption.MdConsumptionMapper;
import com.planitary.entity.mapper.subscription.MdSubscriptionMapper;
import com.planitary.entity.model.consumption.MdConsumptionAppInfo;
import com.planitary.entity.model.dto.*;
import com.planitary.entity.model.subscription.MdSubscriptionAppInfo;
import com.planitary.service.md.home.MDHomeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



        // 聚合展示DTO
        HomeInfoDto homeInfoDto = new HomeInfoDto();
        List<String> accountingTypeList = new ArrayList<>();
        // 记账类型
        for (BizEnum bizEnum : BizEnum.values()){
            accountingTypeList.add(bizEnum.getBizCode());
        }
        homeInfoDto.setAccountingType(accountingTypeList);
        // 订阅类型：订阅总额,订阅APP价格相加
        if (Objects.equals(getAppInfo.getAccountingType(), BizEnum.SUBSCRIPTION.getBizCode())){

            // 查找订阅组
            mdSubAppInfoWrapper.eq(MdSubscriptionAppInfo::getSubscriptionUserId,userId);
            List<MdSubscriptionAppInfo> mdSubscriptionAppInfos = mdSubscriptionMapper.selectList(mdSubAppInfoWrapper);
            if (mdSubscriptionAppInfos == null){
                log.info("未找到任何结果");
                MDException.exceptionCast(ExceptionEnum.OBJECT_NULL);
            }

            BigDecimal totalPrice = BigDecimal.ZERO;
            List<SubAppBaseInfo> mdSubscriptionAppInfoList = new ArrayList<>();

            for (MdSubscriptionAppInfo mdSubscriptionAppInfo : mdSubscriptionAppInfos){
                // 订阅app基本信息
                SubAppBaseInfo subAppBaseInfo = new SubAppBaseInfo();
                totalPrice = totalPrice.add(mdSubscriptionAppInfo.getSubscriptionPrice());
                subAppBaseInfo.setSubscriptionPrice(mdSubscriptionAppInfo.getSubscriptionPrice());
                subAppBaseInfo.setAppId(mdSubscriptionAppInfo.getAppId());
                subAppBaseInfo.setAppIcon(mdSubscriptionAppInfo.getAppIcon());
                subAppBaseInfo.setAppName(mdSubscriptionAppInfo.getAppName());
                subAppBaseInfo.setSubscriptionDate(mdSubscriptionAppInfo.getSubscriptionDate());
                mdSubscriptionAppInfoList.add(subAppBaseInfo);
                // 获取app的中文名称
                String appNameDesc = this.getAppNameDesc(mdSubscriptionAppInfo.getAppName());
                subAppBaseInfo.setAppNameDesc(appNameDesc);
            }
            homeInfoDto.setTotalAmounts(totalPrice);
            homeInfoDto.setMdSubscriptionAppInfos(mdSubscriptionAppInfoList);
            homeInfoDto.setSubscriptionCount(mdSubscriptionAppInfos.size());
        }

        //订阅类型：消费总额，消费App相加-收入
        if (Objects.equals(getAppInfo.getAccountingType(),BizEnum.CONSUMPTION.getBizCode())){
            // 查找消费组
            List<ConsumptionAppBaseInfo> consumptionAppBaseInfos = mdConsumptionMapper.getAggregationCostAppInfo(userId);
            if (consumptionAppBaseInfos == null){
                log.info("未找到任何结果");
                MDException.exceptionCast(ExceptionEnum.OBJECT_NULL);
            }
            BigDecimal totalCost = BigDecimal.ZERO;
            BigDecimal totalIncome = consumptionAppBaseInfos.get(0).getTotalIncome();

            for (ConsumptionAppBaseInfo consumptionAppBaseInfo : consumptionAppBaseInfos){
                // 总消费
                totalCost = totalCost.add(consumptionAppBaseInfo.getTotalCost());
                // app中文名称
                String appNameDesc = this.getAppNameDesc(consumptionAppBaseInfo.getAppName());
                consumptionAppBaseInfo.setAppNameDesc(appNameDesc);
            }

            homeInfoDto.setTotalCost(totalCost);
            // 净收入
            BigDecimal totalPrice = totalIncome.subtract(totalCost);
            homeInfoDto.setTotalAmounts(totalPrice);
            homeInfoDto.setMdConsumptionAppInfos(consumptionAppBaseInfos);
            log.info("homeInfoDto:{}",homeInfoDto);
        }
        return homeInfoDto;
    }

    @Override
    @Transactional
    public String addBill(AddBillBaseDTO addBillBaseDTO) {
        //校验用户id
        String userId = addBillBaseDTO.getUserId();
        if (Objects.equals(userId,null)){
            log.error("用户id为空");
            MDException.exceptionCast(ExceptionEnum.SYSTEM_ERROR);
        }
        // 收入记账
        if (Objects.equals(addBillBaseDTO.getAppType(), "INCOME")){

        }
        return "1";
    }


    private String getAppNameDesc(String code){
        try {
            AppEnum appEnum = AppEnum.getTypeByCode(code);
            return appEnum.getBizType();
        }catch (MDException e){
            log.error(e.getMessage());
        }
        return null;
    }

}
