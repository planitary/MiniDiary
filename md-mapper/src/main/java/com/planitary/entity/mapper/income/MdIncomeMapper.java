package com.planitary.entity.mapper.income;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.planitary.entity.model.dto.IncomeBaseAppInfo;
import com.planitary.entity.model.income.IncomeAppInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zane
 * @date 2024-12-16 17:14:10
 */
@Mapper
public interface MdIncomeMapper extends BaseMapper<IncomeAppInfo> {
    // 根据userId聚合计算收入，展示对应收入详情
    List<IncomeBaseAppInfo> getAggregationIncomeInfo(@Param("userId") String userId);

}
