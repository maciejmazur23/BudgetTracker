package com.example.budgettracker.controllers;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.Year;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.service.interfaces.SummaryService;
import com.example.budgettracker.service.interfaces.TransactionService;
import com.example.budgettracker.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SummaryApi {
    private final UserService userService;
    private final TransactionService transactionService;
    private final SummaryService summaryService;

    @GetMapping("/user/summary")
    public String postYear(@ModelAttribute Year year) {
        log.info("Year: [{}]", year);
        int intYear = year.getYear();
        if (year.getYear() == 0) intYear = LocalDate.now().getYear();
        log.debug("Year: [{}]", year);
        return "redirect:/user/summary/" + intYear;
    }

    @GetMapping("/user/summary/{year}")
    public String summary(@PathVariable("year") Integer year, Principal principal, Model model) {
        Summaries summaries = getSummaries(principal);
        addModelAttributes(year, model, summaries);

        return "summary-page";
    }

    private void addModelAttributes(Integer year, Model model, Summaries summaries) {
        List<Summary> yearSummaries = summaries.yearSummaries();
        List<Summary> summariesList = summaryService.getSummariesList(year, summaries, yearSummaries);
        List<Integer> years = summaryService.getYears(yearSummaries);
        List<BigDecimal> incomes = summaryService.getIncomes(summariesList);
        List<BigDecimal> costs = summaryService.getCosts(summariesList);
        Map<CATEGORY, BigDecimal> categoryMap = summaryService.getCategoryCostMap(summariesList);

        logAttributes(yearSummaries, summariesList, years, incomes, costs, categoryMap);

        model.addAttribute("list", summariesList);
        model.addAttribute("years", years);
        model.addAttribute("incomes", incomes);
        model.addAttribute("costs", costs);
        model.addAttribute("categoryCosts", categoryMap);
        model.addAttribute("months", Month.values());
        model.addAttribute("chosenYear", new Year());
    }

    private void logAttributes(
            List<Summary> yearSummaries, List<Summary> summariesList, List<Integer> years,
            List<BigDecimal> incomes, List<BigDecimal> costs, Map<CATEGORY, BigDecimal> categoryMap
    ) {
        log.info("YearSummaries: [{}]", yearSummaries);
        log.info("SummariesList: [{}]", summariesList);
        log.info("Years: [{}]", years);
        log.info("Incomes: [{}]", incomes);
        log.info("Costs: [{}]", costs);
        log.info("Category Map: [{}]", categoryMap);
    }

    private Summaries getSummaries(Principal principal) {
        String email = principal.getName();
        Long userId = userService.getIdByEmail(email);
        List<TransactionEntity> transactionEntities = transactionService.getTransactionsByUserId(userId);
        Summaries summaries = summaryService.getSummaries(transactionEntities);

        log.debug("Email: [{}]", email);
        log.debug("UserId: [{}]", userId);
        log.info("Transactions: [{}]", transactionEntities);
        log.info("Summaries: [{}]", summaries);
        return summaries;
    }
}
