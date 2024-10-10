package com.planitary.core.customResult;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zane
 * @date 2024-10-09 13:47:58
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    // 数据列表
    private List<T> items;

    /**
     * 结果编码
     */
    private String code;

    // 总结果数
    private long totalCounts;

    // 当前页码
    private long page;

    // 其他信息
    private Map<String,String> data;

    // 页大小
    private long pageSize;

    public PageResult(List<T> items,long totalCounts,long page,long pageSize,String code){
        this.items = items;
        this.totalCounts = totalCounts;
        this.page = page;
        this.pageSize = pageSize;
        this.code = code;
    }

    public PageResult(List<T> items,long totalCounts,long page,long pageSize,Map<String,String> data,String code){
        this.items = items;
        this.totalCounts = totalCounts;
        this.page = page;
        this.pageSize = pageSize;
        this.data = data;
        this.code = code;
    }
}
