package com.planitary.core.exception;

import com.planitary.base.commonEnum.ExceptionEnum;
import lombok.Getter;

/**
 * @author zane
 * @date 2024-10-10 16:41:19
 * @description：自定义异常类
 */
@Getter
public class MDException extends RuntimeException{
    private String errCode;

    public MDException(){}

    public MDException(String message){
        super(message);
    }
    public MDException(String message,String code){
        super(message);
        this.errCode = code;
    }

    // 父类runtime异常
    public static void exceptionCast(String message){
        throw new RuntimeException(message);
    }

    // 自定义异常参数
    public static void exceptionCast(String message,String code){
        throw new MDException(message,code);
    }

    // 支持自定义的错误参数
    public static void exceptionCast(ExceptionEnum exceptionEnum, String code){
        throw new MDException(exceptionEnum.getErrMessage(),code);
    }

    // 系统支持的错误参数
    public static void exceptionCast(ExceptionEnum exceptionEnum){
        throw new MDException(exceptionEnum.getErrMessage(),exceptionEnum.getErrCode());
    }

}
