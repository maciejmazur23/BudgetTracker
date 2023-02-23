package com.example.budgettracker.service;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.MonthSummary;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.YearSummary;
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

    public List<Transaction> getSomeTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setDate(LocalDate.now().minusMonths(1));
        transaction1.setOperation(OPERATION.INCOME);
        transaction1.setCategory(null);
        transaction1.setPrice(BigDecimal.valueOf(100));
        transaction1.setDescription("");
        transactions.add(transaction1);
        Transaction transaction2 = new Transaction();
        transaction2.setDate(LocalDate.now().minusDays(12));
        transaction2.setOperation(OPERATION.COST);
        transaction2.setCategory(CATEGORY.ENTERTAINMENT);
        transaction2.setPrice(BigDecimal.valueOf(14));
        transaction2.setDescription("cinema");
        transactions.add(transaction2);
        Transaction transaction3 = new Transaction();
        transaction3.setDate(LocalDate.now().minusDays(10));
        transaction3.setOperation(OPERATION.COST);
        transaction3.setCategory(CATEGORY.SPORT);
        transaction3.setPrice(BigDecimal.valueOf(12));
        transaction3.setDescription("swimming pool");
        transactions.add(transaction3);
        Transaction transaction4 = new Transaction();
        transaction4.setDate(LocalDate.now().minusDays(8));
        transaction4.setOperation(OPERATION.COST);
        transaction4.setCategory(CATEGORY.SHOPPING);
        transaction4.setPrice(BigDecimal.valueOf(13));
        transaction4.setDescription("fruits");
        transactions.add(transaction4);
        Transaction transaction5 = new Transaction();
        transaction5.setDate(LocalDate.now().minusDays(5));
        transaction5.setOperation(OPERATION.COST);
        transaction5.setCategory(CATEGORY.ENTERTAINMENT);
        transaction5.setPrice(BigDecimal.valueOf(14));
        transaction5.setDescription("cinema");
        transactions.add(transaction5);
        Transaction transaction6 = new Transaction();
        transaction6.setDate(LocalDate.now().minusMonths(2));
        transaction6.setOperation(OPERATION.INCOME);
        transaction6.setCategory(null);
        transaction6.setPrice(BigDecimal.valueOf(200));
        transaction6.setDescription("gift");
        transactions.add(transaction6);
        Transaction transaction7 = new Transaction();
        transaction7.setDate(LocalDate.now().minusMonths(2));
        transaction7.setOperation(OPERATION.COST);
        transaction7.setCategory(CATEGORY.OTHER);
        transaction7.setPrice(BigDecimal.valueOf(200));
        transaction7.setDescription("christmas gifts");
        transactions.add(transaction7);
        Transaction transaction8 = new Transaction();
        transaction8.setDate(LocalDate.now().minusDays(30));
        transaction8.setOperation(OPERATION.COST);
        transaction8.setCategory(CATEGORY.TRANSPORT);
        transaction8.setPrice(BigDecimal.valueOf(16.95));
        transaction8.setDescription("ticket");
        transactions.add(transaction8);
        Transaction transaction9 = new Transaction();
        transaction9.setDate(LocalDate.now());
        transaction9.setOperation(OPERATION.COST);
        transaction9.setCategory(CATEGORY.TRANSPORT);
        transaction9.setPrice(BigDecimal.valueOf(16.95));
        transaction9.setDescription("ticket");
        transactions.add(transaction9);
        Transaction transaction10 = new Transaction();
        transaction10.setDate(LocalDate.now().minusDays(4));
        transaction10.setOperation(OPERATION.COST);
        transaction10.setCategory(CATEGORY.SPORT);
        transaction10.setPrice(BigDecimal.valueOf(12));
        transaction10.setDescription("swimming pool");
        transactions.add(transaction10);

        return transactions;
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

        Summary summaryJanuary2022 = new MonthSummary(
                2022, Month.JANUARY, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryFebruary2022 = new MonthSummary(
                2022, Month.FEBRUARY, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryMarch2022 = new MonthSummary(
                2022, Month.MARCH, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryApril2022 = new MonthSummary(
                2022, Month.APRIL, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryMay2022 = new MonthSummary(
                2022, Month.MAY, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryJune2022 = new MonthSummary(
                2022, Month.JUNE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryJuly2022 = new MonthSummary(
                2022, Month.JULY, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryAugust2022 = new MonthSummary(
                2022, Month.AUGUST, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summarySeptember2022 = new MonthSummary(
                2022, Month.SEPTEMBER, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryOctober2022 = new MonthSummary(
                2022, Month.OCTOBER, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryNovember2022 = new MonthSummary(
                2022, Month.NOVEMBER, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.ZERO,
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryDecember2022 = new MonthSummary(
                2022, Month.DECEMBER, BigDecimal.ZERO, BigDecimal.valueOf(200), BigDecimal.valueOf(200), BigDecimal.ZERO, Map.of(
                CATEGORY.FOOD, BigDecimal.ZERO,
                CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                CATEGORY.SPORT, BigDecimal.ZERO,
                CATEGORY.SHOPPING, BigDecimal.ZERO,
                CATEGORY.HOUSE, BigDecimal.ZERO,
                CATEGORY.OTHER, BigDecimal.valueOf(200),
                CATEGORY.TRANSPORT, BigDecimal.ZERO
        ));
        Summary summaryJanuary2023 = new MonthSummary(
                2023, Month.JANUARY, BigDecimal.ZERO, BigDecimal.valueOf(100), BDWithStaticScale(16.95), BDWithStaticScale(83.05),
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BDWithStaticScale(16.95)
                ));
        Summary summaryFebruary2023 = new MonthSummary(
                2023, Month.FEBRUARY, BDWithStaticScale(83.05), BigDecimal.ZERO, BDWithStaticScale(81.95),
                BigDecimal.ZERO.subtract(BDWithStaticScale(81.95)),
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.valueOf(28),
                        CATEGORY.SPORT, BigDecimal.valueOf(24),
                        CATEGORY.SHOPPING, BigDecimal.valueOf(13),
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BDWithStaticScale(16.95)
                ));
        Summary summaryMarch2023 = new MonthSummary(
                2023, Month.MARCH, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summaryApril2023 = new MonthSummary(
                2023, Month.APRIL, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summaryMay2023 = new MonthSummary(
                2023, Month.MAY, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summaryJune2023 = new MonthSummary(
                2023, Month.JUNE, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summaryJuly2023 = new MonthSummary(
                2023, Month.JULY, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summaryAugust2023 = new MonthSummary(
                2023, Month.AUGUST, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summarySeptember2023 = new MonthSummary(
                2023, Month.SEPTEMBER, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summaryOctober2023 = new MonthSummary(
                2023, Month.OCTOBER, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summaryNovember2023 = new MonthSummary(
                2023, Month.NOVEMBER, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summaryDecember2023 = new MonthSummary(
                2023, Month.DECEMBER, BDWithStaticScale(1.10), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
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
        Summary summary2022 = new YearSummary(
                2022, BigDecimal.ZERO, BigDecimal.valueOf(200), BigDecimal.valueOf(200), BigDecimal.ZERO,
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.ZERO,
                        CATEGORY.SPORT, BigDecimal.ZERO,
                        CATEGORY.SHOPPING, BigDecimal.ZERO,
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.valueOf(200),
                        CATEGORY.TRANSPORT, BigDecimal.ZERO
                ));
        Summary summary2023 = new YearSummary(
                2023, BigDecimal.ZERO, BigDecimal.valueOf(100),
                BDWithStaticScale(98.90), BDWithStaticScale(1.10),
                Map.of(
                        CATEGORY.FOOD, BigDecimal.ZERO,
                        CATEGORY.ENTERTAINMENT, BigDecimal.valueOf(28),
                        CATEGORY.SPORT, BigDecimal.valueOf(24),
                        CATEGORY.SHOPPING, BigDecimal.valueOf(13),
                        CATEGORY.HOUSE, BigDecimal.ZERO,
                        CATEGORY.OTHER, BigDecimal.ZERO,
                        CATEGORY.TRANSPORT, BDWithStaticScale(33.90)
                ));

        yearSummaries.add(summary2022);
        yearSummaries.add(summary2023);
        return yearSummaries;
    }
}
