package com.example.budgettracker.service;

import com.example.budgettracker.entities.UserEntity;
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
class UserEntityServiceImplTest {

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

        String email1 = "user1@wp.pl";
        String email2 = "user2@wp.pl";
        String email3 = "user3@wp.pl";

        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();
        UserEntity userEntity3 = new UserEntity();

        userEntity1.setEmail(email1);
        userEntity2.setEmail(email2);
        userEntity3.setEmail(email3);

        userEntity1.setId(expected1);
        userEntity2.setId(expected2);
        userEntity3.setId(expected3);

        BDDMockito.given(userRepo.findByEmail(email1)).willReturn(Optional.of(userEntity1));
        BDDMockito.given(userRepo.findByEmail(email2)).willReturn(Optional.of(userEntity2));
        BDDMockito.given(userRepo.findByEmail(email3)).willReturn(Optional.of(userEntity3));

        //when
        Long result1 = userService.getIdByEmail(email1);
        Long result2 = userService.getIdByEmail(email2);
        Long result3 = userService.getIdByEmail(email3);

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

        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();
        UserEntity userEntity3 = new UserEntity();

        userEntity1.setEmail(email1);
        userEntity2.setEmail(email2);
        userEntity3.setEmail(email3);

        boolean expected1 = false;
        boolean expected2 = false;
        boolean expected3 = false;

        //when
        boolean result1 = userService.saveUser(userEntity1);
        boolean result2 = userService.saveUser(userEntity2);
        boolean result3 = userService.saveUser(userEntity3);

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
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setEmail(email1);

        boolean expected1 = false;

        Mockito.when(userRepo.findByEmail(email1)).thenReturn(Optional.of(new UserEntity()));

        //when
        boolean result1 = userService.saveUser(userEntity1);

        //then
        Assertions.assertEquals(expected1, result1);
    }

    @Test
    @DisplayName("Check save user with correct email pattern and user is not in database")
    void shouldPassIfAllIsCorrect() {
        //given
        String email1 = "user@wp.pl";
        String email2 = "user@gmail.com";

        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();

        userEntity1.setEmail(email1);
        userEntity2.setEmail(email2);

        boolean expected1 = true;
        boolean expected2 = true;

        Mockito.when(userRepo.findByEmail(email1)).thenReturn(Optional.empty());
        Mockito.when(userRepo.findByEmail(email2)).thenReturn(Optional.empty());
        Mockito.when(userRepo.save(userEntity1)).thenReturn(new UserEntity());
        Mockito.when(userRepo.save(userEntity2)).thenReturn(new UserEntity());
        Mockito.when(passwordEncoder.encode(userEntity1.getPassword())).thenReturn("password1");
        Mockito.when(passwordEncoder.encode(userEntity2.getPassword())).thenReturn("password2");

        //when
        boolean result1 = userService.saveUser(userEntity1);
        boolean result2 = userService.saveUser(userEntity2);

        //then
        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);
    }
}