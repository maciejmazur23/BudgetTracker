package com.example.budgettracker.controllers;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.model.TransactionFilter;
import com.example.budgettracker.service.interfaces.TransactionService;
import com.example.budgettracker.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TransactionsApi {
    private final UserService userService;
    private final TransactionService transactionService;

    @GetMapping("/user/delete-transaction/{id}")
    public String deleteTransaction(@PathVariable("id") Long id) {
        log.info("Id: [{}]", id);

        transactionService.deleteById(id);

        return "redirect:/user/transactions";
    }

    @RequestMapping("/user/transactions")
    public String get(@ModelAttribute("filterObject") Optional<TransactionFilter> optionalFilter, Principal principal, Model model) {
        Long id = userService.getIdByEmail(principal.getName());
        log.debug("Id: [{}]", id);

        TransactionFilter filter = transactionService.getFilter(optionalFilter, id);

        log.info("TransactionFilter: [{}]", filter);

        List<TransactionEntity> transactions = transactionService.getTransactionsByFilter(filter);

        log.info("TransactionListByFilter: [{}]", transactions);

        transactions = transactionService.sortTransactions(id, transactions, Comparator.comparing(TransactionEntity::getDate).reversed());

        log.info("SortedTransactionList: [{}]", transactions);

        model.addAttribute("transactionList", transactions);
        model.addAttribute("filterObject", new TransactionFilter());

        return "transaction-page";
    }

    @PostMapping("/user/transactions/filter")
    public String filterPage(@ModelAttribute("filterObject") TransactionFilter filter) {
        return "redirect:/user/transactions";
    }
}
