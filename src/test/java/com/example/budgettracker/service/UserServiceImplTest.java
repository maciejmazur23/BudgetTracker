package com.example.budgettracker.service;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Test
    @DisplayName("Check if user is in database")
    void shouldFindIdByEmail() {
        //given
        long expected1 = 3L;
        long expected2 = 5L;
        long expected3 = 8L;
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setEmail("user1@wp.pl");
        user2.setEmail("user2@wp.pl");
        user3.setEmail("user3@wp.pl");
        user1.setId(expected1);
        user2.setId(expected2);
        user3.setId(expected3);

        BDDMockito.given(userRepo.findByEmail(user1.getEmail())).willReturn(Optional.of(user1));
        BDDMockito.given(userRepo.findByEmail(user2.getEmail())).willReturn(Optional.of(user2));
        BDDMockito.given(userRepo.findByEmail(user3.getEmail())).willReturn(Optional.of(user3));

        //when
        Long result1 = userService.getIdByEmail("user1@wp.pl");
        Long result2 = userService.getIdByEmail("user2@wp.pl");
        Long result3 = userService.getIdByEmail("user3@wp.pl");

        //then
        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
        Assertions.assertEquals(expected3, result3);
    }

    @Test
    @DisplayName("Check if user not exist")
    void shouldFailWhenUserNotExist() {
        //given
        String email = "user@wp.pl";
        Mockito.when(userRepo.findByEmail(email)).thenReturn(Optional.empty());

        //when, then
        Throwable exception = Assertions.assertThrows(RuntimeException.class, () -> userService.getIdByEmail(email));
        Assertions.assertEquals("User not found!", exception.getMessage());
    }

}