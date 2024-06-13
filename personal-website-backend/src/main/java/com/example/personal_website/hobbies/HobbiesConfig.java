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

            Hobbies ff14 = new Hobbies(
                    "Final Fantasy XIV",
                    "Did you know that the critically acclaimed MMORPG Final Fantasy XIV has a free trial, " +
                            "and includes the entirety of A Realm Reborn AND the award-winning Heavensward " +
                            "expansion up to level 60 with no restrictions on playtime?" +
                            " Sign up, " +
                            "and enjoy Eorzea today!",
                    "https://secure.square-enix.com/account/app/svc/ffxivregister?lng=en-gb",
                    clark
            );

            Hobbies youtoob = new Hobbies(
                    "YouTube",
                    "When I feel a creative rush I may post on my channel.",
                    "https://www.youtube.com/@powerbabyy",
                    clark
            );

            Hobbies gitfarming = new Hobbies(
                    "Project Farming",
                    "I take the opportunity to put any of my bulkier projects on my GitHub account.",
                    "https://github.com/krispybataa",
                    clark
            );

            Hobbies fivhuncigs = new Hobbies(
                    "500 Cigarettes.",
                    "The aroma is most pleasing. (YT Render Test).",
                    "https://youtu.be/8liPBsUtND4",
                    clark
            );

            //SHAN HOBBIES

            Hobbies crochet = new Hobbies(
                    "Crocheting",
                    "I learned to crochet because buying 500 pesos of yarn to make an article of clothing/stuffed toy is basically free.",
                    "https://drive.google.com/file/d/1i-qfU5oPtQL8dC4lxZ10tI5cXOcY_A8j/view?usp=drivesdk",
                    shan
            );

            Hobbies piano = new Hobbies(
                    "Playing the piano/keyboard",
                    "I've been playing the piano for my church since I was 11 and I've been playing it ever since. Lakas maka-clout-chase ng picture so pinili ko talaga 'yan.",
                    "https://drive.google.com/file/d/1OCylyHlnoxzr1In9YGYAD4avyCk7Xt-b/view?usp=drivesdk",
                    shan
            );

            Hobbies kpop = new Hobbies(
                    "KPOP",
                    "I have been consumed by Korean media (125 day duolingo streak). If you're curious, my ults are TXT, Red Velvet, and Kiss of Life!",
                    "https://drive.google.com/file/d/1g9Cxu4chF4IR6WZ7OMwKPKzc9ENKkFUn/view?usp=drivesdk",
                    shan
            );

            hobbiesRepository.saveAll(List.of(ff14, youtoob, gitfarming, crochet, piano, kpop, fivhuncigs));
        };
    }
}
