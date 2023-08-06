package com.example.budgettracker.model;

import com.example.budgettracker.model.enums.CATEGORY;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class TransactionFilter {
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private boolean incomes;
    private boolean costs;
    private List<CATEGORY> categories;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;

    public TransactionFilter() {
        dateFrom = LocalDate.MIN;
        dateTo = LocalDate.MAX;
        incomes = true;
        costs = true;
        categories = new LinkedList<>();
        priceFrom = BigDecimal.ZERO;
        priceTo = BigDecimal.valueOf(Long.MAX_VALUE);
    }
}