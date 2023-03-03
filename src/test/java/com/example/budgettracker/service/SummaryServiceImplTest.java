package com.example.budgettracker.service;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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
        this.summaryService= new SummaryServiceImpl();
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
    @DisplayName("Check if List<Transaction> size = 0")
    void shouldPassIfSizeIsZero(){
        //given
        List<Transaction> transactions = Collections.emptyList();
        int expectedSize = 0;

        //when
        Summaries result = summaryService.getSummaries(transactions);

        //then
        Assertions.assertEquals(expectedSize, result.monthSummaries().size());
        Assertions.assertEquals(expectedSize, result.yearSummaries().size());
    }
}