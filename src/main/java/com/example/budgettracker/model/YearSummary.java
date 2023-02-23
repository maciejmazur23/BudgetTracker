package com.example.budgettracker.model;

import com.example.budgettracker.model.enums.CATEGORY;
import lombok.*;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearSummary implements Summary{
    private int year;
    private BigDecimal previousBalance;
    private BigDecimal incomes;
    private BigDecimal costs;
    private BigDecimal currentBalance;
    private Map<CATEGORY, BigDecimal> categoryCosts;

    @Override
    public Month getMonth() {
        return null;
    }
}
