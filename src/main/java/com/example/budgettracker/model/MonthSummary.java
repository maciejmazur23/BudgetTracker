package com.example.budgettracker.model;

import com.example.budgettracker.model.enums.CATEGORY;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@Data
public class MonthSummary implements Summary{
    private int year;
    private Month month;
    private BigDecimal previousBalance;
    private BigDecimal incomes;
    private BigDecimal costs;
    private BigDecimal currentBalance;
    private Map<CATEGORY, BigDecimal> categoryCosts;
}
