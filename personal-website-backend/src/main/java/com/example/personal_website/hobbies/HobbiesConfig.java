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

            Hobbies yttest = new Hobbies(
                    "YT Test Render",
                    "This is to test the YT capabilities",
                    "https://youtu.be/I98FB8pcNVA",
                    clark
            );

            //SHAN HOBBIES

            Hobbies crochet = new Hobbies(
                    "Crocheting",
                    "Crocheting a bunch of stuff so I don't have to buy them.",
                    "https://th.bing.com/th/id/OIP.M1ELxfNW5Zju-lzLh4GxuAHaEK?rs=1&pid=ImgDetMain",
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

            hobbiesRepository.saveAll(List.of(ff14, youtoob, gitfarming, crochet, piano, kpop, yttest));
        };
    }
}
