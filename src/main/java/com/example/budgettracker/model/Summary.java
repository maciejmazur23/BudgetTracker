package com.example.budgettracker.model;

import com.example.budgettracker.model.enums.CATEGORY;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Summary {
    private int year;
    private Month month;
    private BigDecimal previousBalance;
    private BigDecimal incomes;
    private BigDecimal costs;
    private BigDecimal currentBalance;
    private Map<CATEGORY, BigDecimal> categoryCosts;
}
