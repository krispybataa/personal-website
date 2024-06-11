package com.example.personal_website.music;

import com.example.personal_website.users.Users;
import com.example.personal_website.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class MusicConfig {
    @Bean
    @Order(3)
    @Transactional
    CommandLineRunner musiccommandLineRunner(MusicRepository musicRepository, UsersRepository usersRepository){
        return args -> {
            Users clark = usersRepository.findUsersByUsername("krispybataa").orElseThrow(() -> new IllegalStateException("User not found"));
            Users shan = usersRepository.findUsersByUsername("deenonuggets").orElseThrow(() -> new IllegalStateException("User not found"));
            //CLARK MUSIC CONFIG
            Music bibbidiba = new Music(
                    "Bibbidiba",
                    "Bibbidiba",
                    "Hoshimachi Suisei",
                    "https://images.genius.com/6aacf4656a6d6e335444edd1c456ea56.1000x1000x1.png",
                    "https://open.spotify.com/track/0ShUHmWaz48KgyjaOG7Tpv?si=f09aae530b3741dc",
                    clark
            );
            Music pinkvenom = new Music(
                    "Pink Venom",
                    "Pink Venom",
                    "BLACKPINK",
                    "https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Pink_Venom_Cover.jpg/220px-Pink_Venom_Cover.jpg",
                    "https://open.spotify.com/track/7EyhPjrJzjx0fk2i7vUJCS?si=2e76b50948ce4fb6",
                    clark
            );
            Music chuchu = new Music(
                    "ChuChu Lovely MuniMuni MuraMura PrinPrin Boron Nururu ReroRero",
                    "Bu-ikikaesu",
                    "MAXIMUM THE HORMONE",
                    "https://t2.genius.com/unsafe/340x340/https%3A%2F%2Fimages.genius.com%2F47407c91e1a2b50d02849ae3569ceb93.1000x1000x1.jpg",
                    "https://open.spotify.com/track/4vLhi74fctCnK4OFbiiS6P?si=d671f6b96dd446d2",
                    clark
            );
            Music myway = new Music(
                    "My Way",
                    "My Way (Expanded Edition)",
                    "Frank Sinatra",
                    "https://i.scdn.co/image/ab67616d0000b27380aa6a82fce614eea8357253",
                    "https://open.spotify.com/track/3spdoTYpuCpmq19tuD0bOe?si=aec955daf8e541e5",
                    clark
            );
            Music mirror = new Music(
                    "MIRROR",
                    "MIRROR",
                    "Ado",
                    "https://i.scdn.co/image/ab67616d00001e02dfdacb364c44d7365b6139b7",
                    "https://open.spotify.com/track/0NtfH5RUt4V3Vzh18Wuc23?si=689e08d46d9140e5",
                    clark
            );
            musicRepository.saveAll(List.of(bibbidiba,mirror, myway, chuchu, pinkvenom));

            //SHAN MUSIC CONFIG
            Music magnetic = new Music(
                    "Magnetic",
                    "SUPER REAL ME",
                    "ILLIT",
                    "https://upload.wikimedia.org/wikipedia/en/9/99/Illit_-_Super_Real_Me.png",
                    "https://open.spotify.com/track/1aKvZDoLGkNMxoRYgkckZG?si=c9109785a4ab4e12",
                    shan
            );
            musicRepository.saveAll(List.of(magnetic));
        };
    }
}
