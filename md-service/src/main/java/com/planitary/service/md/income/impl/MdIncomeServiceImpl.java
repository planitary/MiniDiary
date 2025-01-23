package com.planitary.service.md.income.impl;

import com.planitary.base.commonEnum.BizEnum;
import com.planitary.base.commonEnum.ExceptionEnum;
import com.planitary.base.utils.GlobalUniqueGenerator;
import com.planitary.core.exception.MDException;
import com.planitary.entity.mapper.income.MdIncomeMapper;
import com.planitary.entity.model.dto.AddBillBaseDTO;
import com.planitary.entity.model.income.IncomeAppInfo;
import com.planitary.service.md.income.MDIncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author zane
 * @date 2025-01-22 17:47:25
 */
@Service
@Slf4j
public class MdIncomeServiceImpl implements MDIncomeService {

    @Autowired
    private MdIncomeMapper mdIncomeMapper;
    @Autowired
    private GlobalUniqueGenerator globalUniqueGenerator;
    @Override
    @Transactional
    public String addBill(AddBillBaseDTO addBillBaseDTO) {
            //校验用户id
            String userId = addBillBaseDTO.getUserId();
            if (Objects.equals(userId,null)){
                log.error("用户id为空");
                MDException.exceptionCast(ExceptionEnum.SYSTEM_ERROR);
            }
            IncomeAppInfo incomeAppInfo = new IncomeAppInfo();
            String incomeRecordId = globalUniqueGenerator.idGenerator();
            // 收入记账
            if (Objects.equals(addBillBaseDTO.getBillType(), BizEnum.INCOME.getBizCode())){
                //格式化amount
                BigDecimal incomeAmount = new BigDecimal(addBillBaseDTO.getAmount());

                incomeAppInfo.setAppId("88990");
                incomeAppInfo.setAppType("INCOME");
                // 记账小类非工资
                if (!Objects.equals(addBillBaseDTO.getAccountingType(), BizEnum.SALARY.getBizType())){
                    incomeAppInfo.setAppName(addBillBaseDTO.getSourceApp());
                    incomeAppInfo.setMerchant(addBillBaseDTO.getMerchant());
                }
                // 记账小类为工资
                else {
                    incomeAppInfo.setAppName(addBillBaseDTO.getBank());
                    String merchantNameWithSalary = addBillBaseDTO.getMerchant();
                    incomeAppInfo.setMerchant(merchantNameWithSalary.toUpperCase());
                }
                incomeAppInfo.setIncomeType(addBillBaseDTO.getAccountingType());
                incomeAppInfo.setSourceType(addBillBaseDTO.getAccountType());
                incomeAppInfo.setIncomePrice(incomeAmount);
                incomeAppInfo.setIncomeDate(addBillBaseDTO.getAccountingTime());
                incomeAppInfo.setUserId(addBillBaseDTO.getUserId());
                incomeAppInfo.setIncomeRecordId(incomeRecordId);
            }
            int insert = mdIncomeMapper.insert(incomeAppInfo);
            if (insert <= 0){
                MDException.exceptionCast(ExceptionEnum.INSERT_ERROR);
            }
            return incomeRecordId;
        }
}
