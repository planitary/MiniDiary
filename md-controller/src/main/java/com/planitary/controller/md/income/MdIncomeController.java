package com.planitary.controller.md.income;

import com.planitary.core.customResult.MDResult;
import com.planitary.entity.model.dto.AddBillBaseDTO;
import com.planitary.service.md.income.MDIncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zane
 * @date 2025-01-22 17:40:05
 */
@RestController
@Slf4j
public class MdIncomeController {

    @Autowired
    MDIncomeService mdIncomeService;

    @PostMapping("/core/income/addIncomeBill")
    public MDResult<String> addIncomeBill(@RequestBody AddBillBaseDTO addBillBaseDTO){
        String incomeRecordId = mdIncomeService.addBill(addBillBaseDTO);
        return MDResult.success(incomeRecordId);
    }
}
