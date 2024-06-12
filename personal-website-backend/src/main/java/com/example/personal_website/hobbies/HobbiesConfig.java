package com.example.personal_website.hobbies;

import com.example.personal_website.users.Users;
import com.example.personal_website.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class HobbiesConfig {
    @Bean
    @Order(4)
    @Transactional
    CommandLineRunner hobbiescommandLineRunner(HobbiesRepository hobbiesRepository, UsersRepository usersRepository){
        return args -> {
            Users clark = usersRepository.findUsersByUsername("krispybataa").orElseThrow(() -> new IllegalStateException("User not found"));
            Users shan = usersRepository.findUsersByUsername("deenonuggets").orElseThrow(() -> new IllegalStateException("User not found"));

            Hobbies farming = new Hobbies(
                    "Farming Levels",
                    "Levelling up Subclasses",
                    "https://youtu.be/YwILuJ5lVwM",
                    clark
            );

            Hobbies givingup = new Hobbies(
                    "Not Giving you Up",
                    "Not letting you go",
                    "https://youtu.be/dQw4w9WgXcQ",
                    clark
            );

            Hobbies crochet = new Hobbies(
                    "Crocheting",
                    "Crocheting a bunch of stuff so I don't have to buy them.",
                    "/images/crochet.png",
                    shan
            );

            Hobbies piano = new Hobbies(
                    "Playing the piano/keyboard",
                    "I am a fakeyboardist, the picture is only for cloutchasing purposes.",
                    "/images/piano.png",
                    shan
            );

            Hobbies kpop = new Hobbies(
                    "KPOP",
                    "I have been consumed by Korean media (125 day duolingo streak)",
                    "/images/kpop.png",
                    shan
            );

            hobbiesRepository.saveAll(List.of(farming, givingup, crochet, piano, kpop));
        };
    }
}
