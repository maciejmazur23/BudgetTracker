package com.example.budgettracker.service.impl;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.service.interfaces.SummaryCreator;
import com.example.budgettracker.service.interfaces.SummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final SummaryCreator summaryCreator;

    @Override
    public Summaries getSummaries(List<TransactionEntity> transactionEntities) {
        List<Summary> yearSummaries = new ArrayList<>();
        List<Summary> monthSummaries = new ArrayList<>();

        if (transactionEntities.size() > 0) {
            summaryCreator.setMonthAndYearSummaries(yearSummaries, monthSummaries, transactionEntities);
            log.info("YearSummaries: [{}]", yearSummaries);
            log.info("MonthSummaries: [{}]", monthSummaries);
        }
        return new Summaries(monthSummaries, yearSummaries);
    }

    @Override
    public BigDecimal getTotalBalance(List<Summary> yearSummaries) {
        Summary thisYearSummary = yearSummaries.stream()
                .filter(summary -> summary.getYear() == LocalDate.now().getYear())
                .findFirst()
                .orElse(newSummary());

        return thisYearSummary.getPreviousBalance()
                .add(thisYearSummary.getCurrentBalance());
    }

    private Summary newSummary() {
        return Summary.builder()
                .year(LocalDate.now().getYear())
                .month(LocalDate.now().getMonth())
                .previousBalance(BigDecimal.ZERO)
                .incomes(BigDecimal.ZERO)
                .costs(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .categoryCosts(generateNewCategoryCost())
                .build();
    }

    private Map<CATEGORY, BigDecimal> generateNewCategoryCost() {
        Map<CATEGORY, BigDecimal> categoryMap = new HashMap<>();

        Arrays.stream(CATEGORY.values()).forEach(category ->
                categoryMap.put(category, BigDecimal.ZERO)
        );

        return categoryMap;
    }

    @Override
    public List<BigDecimal> getCosts(List<Summary> summariesList) {
        return summariesList.stream()
                .map(Summary::getCosts)
                .toList();
    }

    @Override
    public List<BigDecimal> getIncomes(List<Summary> summariesList) {
        return summariesList.stream()
                .map(Summary::getIncomes)
                .toList();
    }

    @Override
    public List<Integer> getYears(List<Summary> yearSummaries) {
        return yearSummaries.stream()
                .map(Summary::getYear)
                .toList();
    }

    @Override
    public List<Summary> getSummariesList(Integer year, Summaries summaries, List<Summary> yearSummaries) {
        List<Summary> summariesList;

        if (year == 1) summariesList = yearSummaries;
        else summariesList = summaries.monthSummaries().stream()
                .filter(summary -> summary.getYear() == year)
                .toList();

        return summariesList;
    }

    @Override
    public Map<CATEGORY, BigDecimal> getCategoryCostMap(List<Summary> summariesList) {
        Map<CATEGORY, BigDecimal> categoryMap = new HashMap<>();

        for (CATEGORY category : CATEGORY.values()) {
            categoryMap.put(category, BigDecimal.ZERO);
        }

        summariesList.forEach(summary -> {
            Map<CATEGORY, BigDecimal> categoryCosts = summary.getCategoryCosts();
            categoryCosts.forEach(
                    (key, value) -> categoryMap.replace(key, categoryMap.get(key).add(value))
            );
        });
        return categoryMap;
    }

}