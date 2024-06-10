package com.example.personal_website.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner userscommandLineRunner(UsersRepository usersRepository){
        return args -> {
            Users clark = new Users(
                    "Clark",
                    "krispybataa",
                    LocalDate.of(2005,4, 28)
            );

            Users shan = new Users(
                    "Sheianne",
                    "deenonuggets",
                    LocalDate.of(2005,1,1)
            );

            usersRepository.saveAll(List.of(clark,shan));
        };
    }
}
