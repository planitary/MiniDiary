package com.planitary.service.md.subscription.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.core.exception.MDException;
import com.planitary.entity.mapper.subscription.MdSubscriptionMapper;
import com.planitary.entity.model.subscription.MdSubscriptionAppInfo;
import com.planitary.service.md.subscription.MdSubscriptionAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zane
 * @date 2024-10-11 10:06:48
 */
@Service
@Slf4j
public class MdSubscriptionAppServiceImpl implements MdSubscriptionAppService {

    @Autowired
    MdSubscriptionMapper mdSubscriptionMapper;

    @Override
    public MdSubscriptionAppInfo getSubAppInfo(String userId) {
        if (userId == null){
            log.error("userId为空");
            MDException.exceptionCast("userID为空", ExceptionEnum.PARAMETER_ERROR);
        }
        LambdaQueryWrapper<MdSubscriptionAppInfo> mdSubAppInfoWrapper = new LambdaQueryWrapper<>();
        mdSubAppInfoWrapper.eq(MdSubscriptionAppInfo::getSubscriptionUserId,userId);
        MdSubscriptionAppInfo mdSubscriptionAppInfo = mdSubscriptionMapper.selectOne(mdSubAppInfoWrapper);
        if (mdSubscriptionAppInfo == null){
            log.info("未找到任何结果");
            MDException.exceptionCast(ExceptionEnum.OBJECT_NULL);
        }
        return mdSubscriptionAppInfo;
    }
}
