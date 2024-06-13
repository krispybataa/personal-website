package com.example.personal_website.todos;

import com.example.personal_website.users.Users;
import com.example.personal_website.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class TodosConfig {
    @Bean
    @Order(2)
    @Transactional
    CommandLineRunner todoscommandLineRunner(TodosRepository todosRepository, UsersRepository usersRepository){
        return args -> {
            Users clark = usersRepository.findUsersByUsername("krispybataa").orElseThrow(() -> new IllegalStateException("User not found"));
            Users shan = usersRepository.findUsersByUsername("deenonuggets").orElseThrow(() -> new IllegalStateException("User not found"));
            //CLARK TODOLIST CONFIG
            Todos task_clark = new Todos(
                    "#academics",
                    "finish spring",
                    "self explanatory",
                    clark
            );
            Todos tc2 = new Todos(
                    "#lifestyle",
                    "take measurements",
                    "take measurements of the things in life",
                    clark
            );
            Todos tc3 = new Todos(
                    "#programming",
                    "publish all projects to git",
                    "know thy self",
                    clark
            );
            //SHAN TODOS
            Todos task_shan = new Todos(
                    "#academics",
                    "finish react",
                    "self explanatory",
                    shan
            );
            Todos ts2 = new Todos(
                    "#lifestyle",
                    "finish crochet project",
                    "cute",
                    shan
            );
            todosRepository.saveAll(List.of(task_clark, tc2, tc3, task_shan, ts2));
        };
    }
}
