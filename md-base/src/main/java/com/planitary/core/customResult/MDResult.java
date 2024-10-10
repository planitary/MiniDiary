package com.planitary.core.customResult;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zane
 * @date 2024-10-10 16:39:42
 */
@Data
@NoArgsConstructor
public class MDResult<T> {
    /**
     * 返回编码,默认0为成功的响应
     */
    private String code;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 主体数据
     */
    private T data;

    /**
     * 当前接口的traceId
     */
    private String traceId;

    /**
     * 动态绑定数据
     */
    private Map<Object,Object> map = new HashMap<>();

    // 成功调用的接口返回
    public static <T> MDResult<T> success(T object){
        MDResult<T> ptResult = new MDResult<>();
        ptResult.setData(object);
        ptResult.setCode("0");
        return ptResult;
    }

    // 错误调用的接口返回
    public static <T> MDResult<T> error(String message,String code){
        MDResult<T> mdResult = new MDResult<>();
        mdResult.data = null;
        mdResult.errMsg = message;
        mdResult.code = code;
        mdResult.traceId = MDC.get("traceId");
        return mdResult;
    }
}
