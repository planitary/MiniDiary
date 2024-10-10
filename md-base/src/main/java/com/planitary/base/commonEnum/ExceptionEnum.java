package com.planitary.base.commonEnum;

import lombok.Getter;

/**
 * @author zane
 * @date 2024-10-10 16:42:39
 */
@Getter
public enum ExceptionEnum {
    // 系统级通用异常
    SYSTEM_ERROR("系统异常","-0001"),
    UNKNOWN_ERROR("未知异常,请稍后","-1001"),
    PARAMETER_ERROR("参数错误","-1002"),
    OBJECT_NULL("对象为空","-1003"),
    ENUM_NOT_EXIST("枚举不存在","-1004");
    private final String errMessage;
    private final String errCode;

    ExceptionEnum(String errorMessage,String code){
        this.errMessage = errorMessage;
        this.errCode = code;
    }
}
