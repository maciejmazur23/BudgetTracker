package com.example.budgettracker.service;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.repositories.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

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


    @Test
    @DisplayName("Check save user with wrong email pattern")
    void shouldFailedIfEmailIsWrong() {
        //given
        String email1 = "user.pl";
        String email2 = "user@.com";
        String email3 = "user@wp";
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setEmail(email1);
        user2.setEmail(email2);
        user3.setEmail(email3);

        boolean expected1 = false;
        boolean expected2 = false;
        boolean expected3 = false;

        //when
        boolean result1 = userService.saveUser(user1);
        boolean result2 = userService.saveUser(user2);
        boolean result3 = userService.saveUser(user3);

        //then
        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
        Assertions.assertEquals(expected3, result3);
    }

    @Test
    @DisplayName("Check if user is in database")
    void shouldFailedIfEmailExist(){
        //given
        String email1 = "user@wp.pl";
        User user1 = new User();
        user1.setEmail(email1);

        boolean expected1 = false;

        Mockito.when(userRepo.findByEmail(email1)).thenReturn(Optional.of(new User()));

        //when
        boolean result1 = userService.saveUser(user1);

        //then
        Assertions.assertEquals(expected1, result1);
    }

    @Test
    @DisplayName("Check save user with correct email pattern and user is not in database")
    void shouldPassIfAllIsCorrect() {
        //given
        String email1 = "user@wp.pl";
        String email2 = "user@gmail.com";
        User user1 = new User();
        User user2 = new User();
        user1.setEmail(email1);
        user2.setEmail(email2);

        boolean expected1 = true;
        boolean expected2 = true;

        Mockito.when(userRepo.findByEmail(email1)).thenReturn(Optional.empty());
        Mockito.when(userRepo.findByEmail(email2)).thenReturn(Optional.empty());
        Mockito.when(userRepo.save(user1)).thenReturn(new User());
        Mockito.when(userRepo.save(user2)).thenReturn(new User());
        Mockito.when(passwordEncoder.encode(user1.getPassword())).thenReturn("password1");
        Mockito.when(passwordEncoder.encode(user2.getPassword())).thenReturn("password2");

        //when
        boolean result1 = userService.saveUser(user1);
        boolean result2 = userService.saveUser(user2);

        //then
        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
    }
}