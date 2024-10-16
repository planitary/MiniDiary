package com.planitary.service.md.home.impl;

import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.core.exception.MDException;
import com.planitary.entity.model.dto.HomeInfoDto;
import com.planitary.service.md.home.MDHomeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zane
 * @date 2024-10-12 10:28:28
 */
@Service
@Slf4j
public class MdHomeInfoServiceImpl implements MDHomeInfoService {
    @Override
    public HomeInfoDto getHomeInfoByUserId(String userId) {
        if (userId == null) {
            log.error("userId为空");
            MDException.exceptionCast("userID为空", ExceptionEnum.PARAMETER_ERROR);
        }
        return null;
    }
}
