package com.example.budgettracker.service;

import com.example.budgettracker.entities.TransactionEntity;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.model.enums.OPERATION;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SummaryServiceImplDate {
    private final LocalDate SOME_DATE = LocalDate.of(2023, 2, 23);

    public List<TransactionEntity> getSomeTransactions() {
        ArrayList<TransactionEntity> transactionEntities = new ArrayList<>();

        TransactionEntity transactionEntity1 = new TransactionEntity();
        transactionEntity1.setDate(SOME_DATE.minusMonths(1));
        transactionEntity1.setOperation(OPERATION.INCOME);
        transactionEntity1.setCategory(null);
        transactionEntity1.setPrice(BigDecimal.valueOf(100));
        transactionEntity1.setDescription("");
        transactionEntities.add(transactionEntity1);
        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity2.setDate(SOME_DATE.minusDays(12));
        transactionEntity2.setOperation(OPERATION.COST);
        transactionEntity2.setCategory(CATEGORY.ENTERTAINMENT);
        transactionEntity2.setPrice(BigDecimal.valueOf(14));
        transactionEntity2.setDescription("cinema");
        transactionEntities.add(transactionEntity2);
        TransactionEntity transactionEntity3 = new TransactionEntity();
        transactionEntity3.setDate(SOME_DATE.minusDays(10));
        transactionEntity3.setOperation(OPERATION.COST);
        transactionEntity3.setCategory(CATEGORY.SPORT);
        transactionEntity3.setPrice(BigDecimal.valueOf(12));
        transactionEntity3.setDescription("swimming pool");
        transactionEntities.add(transactionEntity3);
        TransactionEntity transactionEntity4 = new TransactionEntity();
        transactionEntity4.setDate(SOME_DATE.minusDays(8));
        transactionEntity4.setOperation(OPERATION.COST);
        transactionEntity4.setCategory(CATEGORY.SHOPPING);
        transactionEntity4.setPrice(BigDecimal.valueOf(13));
        transactionEntity4.setDescription("fruits");
        transactionEntities.add(transactionEntity4);
        TransactionEntity transactionEntity5 = new TransactionEntity();
        transactionEntity5.setDate(SOME_DATE.minusDays(5));
        transactionEntity5.setOperation(OPERATION.COST);
        transactionEntity5.setCategory(CATEGORY.ENTERTAINMENT);
        transactionEntity5.setPrice(BigDecimal.valueOf(14));
        transactionEntity5.setDescription("cinema");
        transactionEntities.add(transactionEntity5);
        TransactionEntity transactionEntity6 = new TransactionEntity();
        transactionEntity6.setDate(SOME_DATE.minusMonths(2));
        transactionEntity6.setOperation(OPERATION.INCOME);
        transactionEntity6.setCategory(null);
        transactionEntity6.setPrice(BigDecimal.valueOf(200));
        transactionEntity6.setDescription("gift");
        transactionEntities.add(transactionEntity6);
        TransactionEntity transactionEntity7 = new TransactionEntity();
        transactionEntity7.setDate(SOME_DATE.minusMonths(2));
        transactionEntity7.setOperation(OPERATION.COST);
        transactionEntity7.setCategory(CATEGORY.OTHER);
        transactionEntity7.setPrice(BigDecimal.valueOf(200));
        transactionEntity7.setDescription("christmas gifts");
        transactionEntities.add(transactionEntity7);
        TransactionEntity transactionEntity8 = new TransactionEntity();
        transactionEntity8.setDate(SOME_DATE.minusDays(30));
        transactionEntity8.setOperation(OPERATION.COST);
        transactionEntity8.setCategory(CATEGORY.TRANSPORT);
        transactionEntity8.setPrice(BigDecimal.valueOf(16.95));
        transactionEntity8.setDescription("ticket");
        transactionEntities.add(transactionEntity8);
        TransactionEntity transactionEntity9 = new TransactionEntity();
        transactionEntity9.setDate(SOME_DATE);
        transactionEntity9.setOperation(OPERATION.COST);
        transactionEntity9.setCategory(CATEGORY.TRANSPORT);
        transactionEntity9.setPrice(BigDecimal.valueOf(16.95));
        transactionEntity9.setDescription("ticket");
        transactionEntities.add(transactionEntity9);
        TransactionEntity transactionEntity10 = new TransactionEntity();
        transactionEntity10.setDate(SOME_DATE.minusDays(4));
        transactionEntity10.setOperation(OPERATION.COST);
        transactionEntity10.setCategory(CATEGORY.SPORT);
        transactionEntity10.setPrice(BigDecimal.valueOf(12));
        transactionEntity10.setDescription("swimming pool");
        transactionEntities.add(transactionEntity10);

        return transactionEntities;
    }

    public Summaries getSomeSummaries() {
        List<Summary> monthSummaries = getMonthSummaries();
        List<Summary> yearSummaries = getYearSummaryList();

        return new Summaries(monthSummaries, yearSummaries);
    }

    private BigDecimal BDWithStaticScale(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

    private List<Summary> getMonthSummaries() {
        List<Summary> summaries = new ArrayList<>();

        Summary summaryJanuary2022 = Summary.builder()
                .year(2022)
                .month(Month.JANUARY)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryFebruary2022 = Summary.builder()
                .year(2022)
                .month(Month.FEBRUARY)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryMarch2022 = Summary.builder()
                .year(2022)
                .month(Month.MARCH)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryApril2022 = Summary.builder()
                .year(2022)
                .month(Month.APRIL)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryMay2022 = Summary.builder()
                .year(2022)
                .month(Month.MAY)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryJune2022 = Summary.builder()
                .year(2022)
                .month(Month.JUNE)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryJuly2022 = Summary.builder()
                .year(2022)
                .month(Month.JULY)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryAugust2022 = Summary.builder()
                .year(2022)
                .month(Month.AUGUST)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summarySeptember2022 = Summary.builder()
                .year(2022)
                .month(Month.SEPTEMBER)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryOctober2022 = Summary.builder()
                .year(2022)
                .month(Month.OCTOBER)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryNovember2022 = Summary.builder()
                .year(2022)
                .month(Month.NOVEMBER)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryDecember2022 = Summary.builder()
                .year(2022)
                .month(Month.DECEMBER)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.valueOf(200))
                .costs(BigDecimal.valueOf(200))
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.valueOf(200),
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryJanuary2023 = Summary.builder()
                .year(2023)
                .month(Month.JANUARY)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.valueOf(100))
                .costs(BDWithStaticScale(16.95))
                .currentBalance(BDWithStaticScale(83.05))
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BDWithStaticScale(16.95)))
                .build();
        Summary summaryFebruary2023 = Summary.builder()
                .year(2023)
                .month(Month.FEBRUARY)
                .previousBalance(BDWithStaticScale(83.05))
                .incomes(BigDecimal.ZERO)
                .costs(BDWithStaticScale(81.95))
                .currentBalance(BigDecimal.ZERO.subtract(BDWithStaticScale(81.95)))
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.valueOf(28),
                        CATEGORY.SPORT, BigDecimal.valueOf(24),
                        CATEGORY.SHOPPING, BigDecimal.valueOf(13),
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BDWithStaticScale(16.95)))
                .build();
        Summary summaryMarch2023 = Summary.builder()
                .year(2023)
                .month(Month.MARCH)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryApril2023 = Summary.builder()
                .year(2023)
                .month(Month.APRIL)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryMay2023 = Summary.builder()
                .year(2023)
                .month(Month.MAY)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryJune2023 = Summary.builder()
                .year(2023)
                .month(Month.JUNE)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryJuly2023 = Summary.builder()
                .year(2023)
                .month(Month.JULY)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryAugust2023 = Summary.builder()
                .year(2023)
                .month(Month.AUGUST)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summarySeptember2023 = Summary.builder()
                .year(2023)
                .month(Month.SEPTEMBER)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryOctober2023 = Summary.builder()
                .year(2023)
                .month(Month.OCTOBER)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryNovember2023 = Summary.builder()
                .year(2023)
                .month(Month.NOVEMBER)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summaryDecember2023 = Summary.builder()
                .year(2023)
                .month(Month.DECEMBER)
                .previousBalance(BDWithStaticScale(1.10))
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();

        summaries.add(summaryJanuary2022);
        summaries.add(summaryFebruary2022);
        summaries.add(summaryMarch2022);
        summaries.add(summaryApril2022);
        summaries.add(summaryMay2022);
        summaries.add(summaryJune2022);
        summaries.add(summaryJuly2022);
        summaries.add(summaryAugust2022);
        summaries.add(summarySeptember2022);
        summaries.add(summaryOctober2022);
        summaries.add(summaryNovember2022);
        summaries.add(summaryDecember2022);
        summaries.add(summaryJanuary2023);
        summaries.add(summaryFebruary2023);
        summaries.add(summaryMarch2023);
        summaries.add(summaryApril2023);
        summaries.add(summaryMay2023);
        summaries.add(summaryJune2023);
        summaries.add(summaryJuly2023);
        summaries.add(summaryAugust2023);
        summaries.add(summarySeptember2023);
        summaries.add(summaryOctober2023);
        summaries.add(summaryNovember2023);
        summaries.add(summaryDecember2023);
        return summaries;
    }

    private List<Summary> getYearSummaryList() {
        List<Summary> yearSummaries = new ArrayList<>();
        Summary summary2022 = Summary.builder()
                .year(2022)
                .month(null)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.valueOf(200))
                .costs(BigDecimal.valueOf(200))
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.valueOf(200),
                        CATEGORY.TRANSPORT, BigDecimal.ZERO))
                .build();
        Summary summary2023 = Summary.builder()
                .year(2023)
                .month(null)
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.valueOf(100))
                .costs(BDWithStaticScale(98.90))
                .currentBalance(BDWithStaticScale(1.10))
                .categoryCosts(Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.valueOf(28),
                        CATEGORY.SPORT, BigDecimal.valueOf(24),
                        CATEGORY.SHOPPING, BigDecimal.valueOf(13),
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BDWithStaticScale(33.90)))
                .build();

        yearSummaries.add(summary2022);
        yearSummaries.add(summary2023);
        return yearSummaries;
    }
}
