package com.planitary.controller.md.home;

import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.core.customResult.MDResult;
import com.planitary.core.exception.MDException;
import com.planitary.entity.model.dto.GetAppInfo;
import com.planitary.entity.model.dto.HomeInfoDto;
import com.planitary.entity.model.income.IncomeAppInfo;
import com.planitary.service.md.home.MDHomeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * @author zane
 * @date 2024-10-25 15:58:16
 */
@RestController
@Slf4j
public class MdHomeController {

    @Autowired
    MDHomeInfoService mdHomeInfoService;

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
    @PostMapping("/core/home/getIncomeInfo")
    public MDResult<IncomeAppInfo> getIncomeInfoByRecordId(@RequestBody Map<String, Object> requestBody){
        String recordId = (String) requestBody.get("recordId");
        if (recordId == null){
            log.error("参数为空");
            MDException.exceptionCast("参数为空",ExceptionEnum.PARAMETER_ERROR);
        }
        IncomeAppInfo incomeAppInfo = mdHomeInfoService.getIncomeInfoByRecordId(recordId);
        if (incomeAppInfo != null){
            log.info("拿到income信息:{}",incomeAppInfo);
        }
        return MDResult.success(incomeAppInfo);
    }
}
