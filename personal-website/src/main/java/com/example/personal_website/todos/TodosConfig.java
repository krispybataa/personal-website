package com.example.personal_website.todos;

import com.example.personal_website.users.Users;
import com.example.personal_website.users.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TodosConfig {
    @Bean
    CommandLineRunner todoscommandLineRunner(TodosRepository todosRepository, UsersRepository usersRepository){
        return args -> {
            Users clark = usersRepository.findUsersByUsername("krispybataa").orElseThrow(() -> new IllegalStateException("User not found"));
            Users shan = usersRepository.findUsersByUsername("deenonuggets").orElseThrow(() -> new IllegalStateException("User not found"));
            Todos task_clark = new Todos(
                    "academics",
                    "finish spring",
                    "self explanatory",
                    clark
            );
            Todos task_shan = new Todos(
                    "academics",
                    "finish react",
                    "self explanatory",
                    shan
            );
            todosRepository.saveAll(List.of(task_clark, task_shan));
        };
    }
}
