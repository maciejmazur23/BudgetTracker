package com.example.budgettracker.service;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.service.impl.SummaryCreatorImpl;
import com.example.budgettracker.service.impl.SummaryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SummaryServiceImplTest {

    private SummaryServiceImplDate summaryServiceImplDate;
    private SummaryServiceImpl summaryService;

    @BeforeEach
    void beforeEach() {
        this.summaryServiceImplDate = new SummaryServiceImplDate();
        this.summaryService = new SummaryServiceImpl(new SummaryCreatorImpl());
    }

    @Test
    @DisplayName("Check summaries correctness")
    void getSummaries() {
        //given
        var transactions = summaryServiceImplDate.getSomeTransactions();

        Summaries expected = summaryServiceImplDate.getSomeSummaries();
        List<Summary> expectedYears = expected.yearSummaries().stream()
                .sorted(Comparator.comparing(Summary::getYear))
                .toList();
        List<Summary> expectedMonths = expected.monthSummaries().stream()
                .sorted(Comparator.comparing(Summary::getYear).thenComparing(Summary::getMonth))
                .toList();

        //when
        Summaries result = summaryService.getSummaries(transactions);
        List<Summary> resultYears = result.yearSummaries().stream()
                .sorted(Comparator.comparing(Summary::getYear))
                .toList();
        List<Summary> resultMonths = result.monthSummaries().stream()
                .sorted(Comparator.comparing(Summary::getYear).thenComparing(Summary::getMonth))
                .toList();

        //then
        for (int i = 0; i < expectedYears.size(); i++) {
            Assertions.assertEquals(expectedYears.get(i), resultYears.get(i));
        }
        for (int i = 0; i < expectedMonths.size(); i++) {
            Assertions.assertEquals(expectedMonths.get(i), resultMonths.get(i));
        }
    }

    @Test
    @DisplayName("Check total balance correctness")
    void getTotalBalance(){
        // given
        Summaries someSummaries = summaryServiceImplDate.getSomeSummaries();
        BigDecimal expected = new BigDecimal("1.10");

        // when
        BigDecimal result = summaryService.getTotalBalance(someSummaries.yearSummaries());

        //then
        Assertions.assertEquals(expected, result);
    }


    @Test
    @DisplayName("Check if List<Transaction> size = 0")
    void shouldPassIfSizeIsZero() {
        //given
        List<TransactionEntity> transactionEntities = Collections.emptyList();
        int expectedSize = 0;

        //when
        Summaries result = summaryService.getSummaries(transactionEntities);

        //then
        Assertions.assertEquals(expectedSize, result.monthSummaries().size());
        Assertions.assertEquals(expectedSize, result.yearSummaries().size());
    }
}