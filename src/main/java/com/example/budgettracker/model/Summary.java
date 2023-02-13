package com.example.budgettracker.model;

import com.example.budgettracker.model.enums.CATEGORY;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

public interface Summary {
    void setYear(int year);

    void setPreviousBalance(BigDecimal previousBalance);

    void setIncomes(BigDecimal incomes);

    void setCosts(BigDecimal costs);

    void setCurrentBalance(BigDecimal currentBalance);

    void setCategoryCosts(Map<CATEGORY, BigDecimal> categoryCosts);

    int getYear();

    Month getMonth();

    BigDecimal getPreviousBalance();

    BigDecimal getIncomes();

    BigDecimal getCosts();

    BigDecimal getCurrentBalance();

    Map<CATEGORY, BigDecimal> getCategoryCosts();
}
