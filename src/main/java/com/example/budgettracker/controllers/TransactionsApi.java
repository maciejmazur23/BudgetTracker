package com.example.budgettracker.controllers;

import com.example.budgettracker.entities.TransactionEntity;
import com.example.budgettracker.service.TransactionService;
import com.example.budgettracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

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

    @PostMapping("/user/add-transaction")
    public String addTransaction(@ModelAttribute("newOperation") TransactionEntity transactionEntity, Principal principal) {
        log.info("Transaction: [{}]", transactionEntity);
        Long id = userService.getIdByEmail(principal.getName());
        log.debug("Id: [{}]", id);
        transactionEntity.setUserId(id);
        transactionService.saveTransaction(transactionEntity);

        return "redirect:/user/transactions";
    }

    @GetMapping("/user/transactions")
    public String get(Principal principal, Model model) {
        Long id = userService.getIdByEmail(principal.getName());
        log.debug("Id: [{}]", id);
        List<TransactionEntity> transactionEntityList = getSortedTransactionsById(
                id,
                Comparator.comparing(TransactionEntity::getDate).reversed()
        );
        log.info("TransactionList: [{}]", transactionEntityList);

        model.addAttribute("transactionList", transactionEntityList);
        model.addAttribute("newTransaction", new TransactionEntity());

        return "transaction-page";
    }

    private List<TransactionEntity> getSortedTransactionsById(Long id, Comparator<TransactionEntity> comparator) {
        return transactionService.getTransactionsByUserId(id).stream()
                .sorted(comparator)
                .toList();
    }
}
