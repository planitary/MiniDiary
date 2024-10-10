package com.planitary.core.exception;

import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.core.customResult.MDResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zane
 * @date 2024-10-10 16:45:35
 *  @description：    全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
    // 处理自定义的的异常（此类异常为可预知异常）
    @ExceptionHandler(MDException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)       // 抛出异常后状态码返回500
    public MDResult<Object> doATPlatformException(MDException e){
        log.error("捕获自定义异常:{}",e.getMessage());
        String errMessage = e.getMessage();
        String errCode = e.getErrCode();
        return MDResult.error(errMessage,errCode);
    }
    // 捕获不可预知的异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MDResult<Object> doRuntimeException(RuntimeException e){
        log.error("捕获系统异常:{}",e.getMessage());
//        String traceId = MDC.get("traceId");
        String errMessage = e.getMessage();
        return MDResult.error(errMessage, ExceptionEnum.SYSTEM_ERROR.getErrCode());
    }

    // 父类异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MDResult<Object> doException(Exception e){
        log.error("父类异常:{}",e.getMessage());
//        String traceId = MDC.get("traceId");
        String errMessage = e.getMessage();
        return MDResult.error(errMessage,ExceptionEnum.SYSTEM_ERROR.getErrCode());
    }
}
