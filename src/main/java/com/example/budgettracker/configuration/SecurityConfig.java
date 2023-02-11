package com.example.budgettracker.configuration;

import com.example.budgettracker.repositories.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepo userRepo;

    public SecurityConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void start(){
        com.example.budgettracker.entities.User user = new com.example.budgettracker.entities.User();
        user.setEmail("user@wp.pl");
        user.setPassword(getBcryptPasswordEncoder().encode("user123"));
        user.setRole("USER");
        try {
            userRepo.save(user);
        }catch (Exception e){
            System.out.println("User already exist");
        }
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        start();
        List<com.example.budgettracker.entities.User> users = userRepo.findAll();
        System.out.println(users);
        List<UserDetails> detailsList = users.stream()
                .map(user -> User.withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build()
                ).toList();
        System.out.println(detailsList);
        return new InMemoryUserDetailsManager(detailsList);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (request) -> request.requestMatchers("/**").authenticated()
                )
                .formLogin()
                .defaultSuccessUrl("/operations", true);

        return http.build();
    }
}
