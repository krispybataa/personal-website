package com.example.personal_website.music;

import com.example.personal_website.users.Users;
import com.example.personal_website.users.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MusicConfig {
    @Bean
    CommandLineRunner musiccommandLineRunner(MusicRepository musicRepository, UsersRepository usersRepository){
        return args -> {
            Users clark = usersRepository.findUsersByUsername("krispybataa").orElseThrow(() -> new IllegalStateException("User not found"));
            Users shan = usersRepository.findUsersByUsername("deenonuggets").orElseThrow(() -> new IllegalStateException("User not found"));
            Music magnetic = new Music(
                    "Magnetic",
                    "SUPER REAL ME",
                    "ILLIT",
                    "https://upload.wikimedia.org/wikipedia/en/9/99/Illit_-_Super_Real_Me.png",
                    shan
            );
            Music bibbidiba = new Music(
                    "Bibbidiba",
                    "Bibbidiba",
                    "Hoshimachi Suisei",
                    "https://images.genius.com/6aacf4656a6d6e335444edd1c456ea56.1000x1000x1.png",
                    clark
            );
            musicRepository.saveAll(List.of(magnetic, bibbidiba));
        };
    }
}
