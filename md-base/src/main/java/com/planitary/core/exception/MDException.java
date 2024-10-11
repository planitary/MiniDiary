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

    // 完全自定义的异常参数(msg&code)
    public static void exceptionCast(String message,String code){
        throw new MDException(message,code);
    }

    // 自定义的异常code(仅code)
    public static void exceptionCast(ExceptionEnum exceptionEnum, String code){
        throw new MDException(exceptionEnum.getErrMessage(),code);
    }


    /**
     * 自定义异常信息(仅msg)
     * @param message               自定义异常
     * @param exceptionEnum         系统枚举类，使用系统的枚举类code
     */
    public static void exceptionCast(String message,ExceptionEnum exceptionEnum){
        throw new MDException(message,exceptionEnum.getErrCode());
    }

    // 系统支持的错误参数
    public static void exceptionCast(ExceptionEnum exceptionEnum){
        throw new MDException(exceptionEnum.getErrMessage(),exceptionEnum.getErrCode());
    }

}
