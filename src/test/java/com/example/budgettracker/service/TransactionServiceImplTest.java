package com.example.budgettracker.service;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.model.enums.OPERATION;
import com.example.budgettracker.repositories.TransactionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepo transactionRepo;

    @Test
    void shouldSaveTransactionCorrectly() {
        //given
        var transaction1 = new Transaction();
        var transaction2 = new Transaction();
        var transaction3 = new Transaction();
        transaction1.setOperation(OPERATION.INCOME);
        transaction2.setOperation(OPERATION.COST);
        transaction3.setOperation(OPERATION.COST);
        transaction1.setCategory(null);
        transaction2.setCategory(CATEGORY.FOOD);
        transaction3.setCategory(CATEGORY.ENTERTAINMENT);

        //when
        transactionService.saveTransaction(transaction1);
        transactionService.saveTransaction(transaction2);
        transactionService.saveTransaction(transaction3);

        //then
        Mockito.verify(transactionRepo).save(transaction1);
        Mockito.verify(transactionRepo).save(transaction2);
        Mockito.verify(transactionRepo).save(transaction3);

        BDDMockito.then(transactionRepo)
                .should()
                .save(transaction1);

        BDDMockito.then(transactionRepo)
                .should()
                .save(transaction2);

        BDDMockito.then(transactionRepo)
                .should()
                .save(transaction3);
    }

    @Test
    @DisplayName("Should fail when INCOME & CATEGORY != null ")
    void shouldFailSaveTransaction1() {
        //given
        var transaction1 = new Transaction();
        transaction1.setOperation(OPERATION.INCOME);
        transaction1.setCategory(CATEGORY.OTHER);

        //when, then
        Throwable exception = Assertions.assertThrows(
                RuntimeException.class, () -> transactionService.saveTransaction(transaction1)
        );
        Assertions.assertEquals("Operation INCOME not have category cost!", exception.getMessage());

        BDDMockito.then(transactionRepo)
                .should(Mockito.times(0))
                .save(transaction1);
    }

    @Test
    @DisplayName("Should fail when COST & CATEGORY == null ")
    void shouldFailSaveTransaction2() {
        //given
        var transaction1 = new Transaction();
        transaction1.setOperation(OPERATION.COST);
        transaction1.setCategory(null);

        //when, then
        Throwable exception = Assertions.assertThrows(
                RuntimeException.class, () -> transactionService.saveTransaction(transaction1)
        );
        Assertions.assertEquals("Operation COST must have category cost!", exception.getMessage());

        BDDMockito.then(transactionRepo)
                .should(Mockito.times(0))
                .save(transaction1);
    }

    @Test
    void getTransactionsByUserId() {
        //given
        Long id1 = 1L;
        Long id2 = 4L;
        Long id3 = 3L;
        Long id4 = 11L;
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();
        transaction1.setId(id1);
        transaction2.setId(id2);
        transaction3.setId(id3);
        var expected1 = List.of(transaction1);
        var expected2 = List.of(transaction2);
        var expected3 = List.of(transaction3);
        List<Transaction> expected4 = Collections.emptyList();

        BDDMockito.given(transactionRepo.findByUserId(id1)).willReturn(expected1);
        BDDMockito.given(transactionRepo.findByUserId(id2)).willReturn(expected2);
        BDDMockito.given(transactionRepo.findByUserId(id3)).willReturn(expected3);
        BDDMockito.given(transactionRepo.findByUserId(id4)).willReturn(expected4);

        //when
        var result1 = transactionService.getTransactionsByUserId(id1);
        var result2 = transactionService.getTransactionsByUserId(id2);
        var result3 = transactionService.getTransactionsByUserId(id3);
        var result4 = transactionService.getTransactionsByUserId(id4);

        //then
        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
        Assertions.assertEquals(expected3, result3);
        Assertions.assertEquals(expected4, result4);

        BDDMockito.then(transactionRepo)
                .should(Mockito.times(1))
                .findByUserId(id1);

        BDDMockito.then(transactionRepo)
                .should(Mockito.times(1))
                .findByUserId(id2);

        BDDMockito.then(transactionRepo)
                .should(Mockito.times(1))
                .findByUserId(id3);

        BDDMockito.then(transactionRepo)
                .should(Mockito.times(1))
                .findByUserId(id4);
    }

    @Test
    void deleteById() {
        //given
        Long id1 = 1L;
        Long id2 = 5L;
        Long id3 = 3L;

        //when
        transactionService.deleteById(id1);
        transactionService.deleteById(id2);
        transactionService.deleteById(id3);

        //then
        Mockito.verify(transactionRepo).deleteById(id1);
        Mockito.verify(transactionRepo).deleteById(id2);
        Mockito.verify(transactionRepo).deleteById(id3);

        BDDMockito.then(transactionRepo)
                .should()
                .deleteById(id1);

        BDDMockito.then(transactionRepo)
                .should()
                .deleteById(id2);

        BDDMockito.then(transactionRepo)
                .should()
                .deleteById(id3);
    }
}