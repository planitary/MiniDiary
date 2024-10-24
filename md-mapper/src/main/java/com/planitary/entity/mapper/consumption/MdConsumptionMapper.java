package com.planitary.entity.mapper.consumption;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.planitary.entity.model.consumption.MdConsumptionAppInfo;
import com.planitary.entity.model.dto.ConsumptionAppBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zane
 * @date 2024-10-16 16:55:58
 */
@Mapper
public interface MdConsumptionMapper extends BaseMapper<MdConsumptionAppInfo> {
    // 根据app_id聚合消费记录，展示消费金额
    List<ConsumptionAppBaseInfo> getAggregationCostAppInfo(@Param("userId") String userId);
}
