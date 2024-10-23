package com.planitary.controller.md.subscription;

import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.core.customResult.MDResult;
import com.planitary.core.exception.MDException;
import com.planitary.entity.model.dto.GetAppInfo;
import com.planitary.entity.model.dto.GetSubAppInfoDto;
import com.planitary.entity.model.dto.HomeInfoDto;
import com.planitary.entity.model.subscription.MdSubscriptionAppInfo;
import com.planitary.service.md.home.MDHomeInfoService;
import com.planitary.service.md.subscription.MdSubscriptionAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zane
 * @date 2024-10-11 11:40:21
 */
@RestController
@Slf4j
public class MdSubAppController {

    @Autowired
    MdSubscriptionAppService mdSubscriptionAppService;

    @Autowired
    MDHomeInfoService mdHomeInfoService;

    @PostMapping("/core/sub/getSubAppInfo")
    public MDResult<MdSubscriptionAppInfo> getSubAppInfoByUserId(@RequestBody GetSubAppInfoDto getSubAppInfoDto){
        if (getSubAppInfoDto == null){
            log.error("参数为空");
            MDException.exceptionCast("参数为空", ExceptionEnum.PARAMETER_ERROR);
        }
        MdSubscriptionAppInfo subAppInfo = mdSubscriptionAppService.getSubAppInfo(getSubAppInfoDto.getUserId());
        log.info("拿到订阅信息:{}",subAppInfo);
        return MDResult.success(subAppInfo);
    }

    @PostMapping("/core/home/getHomeInfo")
    public MDResult<HomeInfoDto> getHomeInfoByUserId(@RequestBody GetAppInfo getAppInfo){
        if (getAppInfo == null){
            log.error("参数为空");
            MDException.exceptionCast("参数为空", ExceptionEnum.PARAMETER_ERROR);
        }
        HomeInfoDto homeInfoByUserId = mdHomeInfoService.getHomeInfoByUserId(getAppInfo);
        log.info("拿到聚合信息:{}",homeInfoByUserId);
        return MDResult.success(homeInfoByUserId);
    }
}
