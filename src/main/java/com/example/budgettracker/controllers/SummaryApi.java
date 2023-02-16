package com.example.budgettracker.controllers;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.Year;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.service.SummaryService;
import com.example.budgettracker.service.SummaryServiceImpl;
import com.example.budgettracker.service.TransactionService;
import com.example.budgettracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SummaryApi {
    private final UserService userService;
    private final TransactionService transactionService;
    private final SummaryService summaryService;

    public SummaryApi(UserService userService, TransactionService transactionService, SummaryServiceImpl summaryService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.summaryService = summaryService;
    }

    @GetMapping("/user/summary/{year}")
    public String summary(@PathVariable("year") Integer year, Principal principal, Model model) {
        String email = principal.getName();
        Long userId = userService.getIdByEmail(email);
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        Summaries summaries = summaryService.getSummaries(transactions);

        List<Summary> yearSummaries = summaries.yearSummaries();
        List<Summary> summariesList = getSummariesList(year, summaries, yearSummaries);
        List<Integer> years = getYears(yearSummaries);
        List<BigDecimal> incomes = getIncomes(summariesList);
        List<BigDecimal> costs = getCosts(summariesList);
        Map<CATEGORY, BigDecimal> categoryMap = getCategoryCostMap(summariesList);

        model.addAttribute("list", summariesList);
        model.addAttribute("years", years);
        model.addAttribute("incomes", incomes);
        model.addAttribute("costs", costs);
        model.addAttribute("categoryCosts", categoryMap);
        model.addAttribute("months", Month.values());
        model.addAttribute("chosenYear", new Year());

        return "summary-page";
    }

    private List<BigDecimal> getCosts(List<Summary> summariesList) {
        return summariesList.stream()
                .map(Summary::getCosts)
                .toList();
    }

    private List<BigDecimal> getIncomes(List<Summary> summariesList) {
        return summariesList.stream()
                .map(Summary::getIncomes)
                .toList();
    }

    private List<Integer> getYears(List<Summary> yearSummaries) {
        return yearSummaries.stream()
                .map(Summary::getYear)
                .toList();
    }

    private List<Summary> getSummariesList(Integer year, Summaries summaries, List<Summary> yearSummaries) {
        List<Summary> summariesList;

        if (year == 1) summariesList = yearSummaries;
        else summariesList = summaries.monthSummaries().stream()
                .filter(summary -> summary.getYear() == year)
                .toList();

        return summariesList;
    }

    private Map<CATEGORY, BigDecimal> getCategoryCostMap(List<Summary> summariesList) {
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

    @GetMapping("/user/summary")
    public String postYear(@ModelAttribute Year year) {
        int intYear = year.getYear();
        if (year.getYear() == 0) intYear = LocalDate.now().getYear();

        return "redirect:/user/summary/" + intYear;
    }
}