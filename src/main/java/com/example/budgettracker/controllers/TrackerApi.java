package com.example.budgettracker.controllers;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.service.interfaces.SummaryService;
import com.example.budgettracker.service.interfaces.TransactionService;
import com.example.budgettracker.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TrackerApi {

    private final TransactionService transactionService;
    private final SummaryService summaryService;
    private final UserService userService;

    @GetMapping("/user")
    public String menu(Principal principal, Model model) {
        Long id = userService.getIdByEmail(principal.getName());
        List<TransactionEntity> transactions = transactionService.getTransactionsByUserId(id);
        BigDecimal balance = getBalance(transactions);
        log.info("Balance: [{}]", balance);

        model.addAttribute("newTransaction", new TransactionEntity());
        model.addAttribute("balance", balance);
        return "menu-page";
    }

    private BigDecimal getBalance(List<TransactionEntity> transactions) {
        List<Summary> yearSummaries = summaryService
                .getSummaries(transactions)
                .yearSummaries();

        return summaryService.getTotalBalance(yearSummaries);
    }

    @PostMapping("/user/add")
    public String addTransaction(@ModelAttribute("newOperation") TransactionEntity transactionEntity, Principal principal) {
        log.info("Transaction: [{}]", transactionEntity);
        Long id = userService.getIdByEmail(principal.getName());
        log.debug("Id: [{}]", id);
        transactionEntity.setUserId(id);
        transactionService.saveTransaction(transactionEntity);

        return "redirect:/user";
    }
}
