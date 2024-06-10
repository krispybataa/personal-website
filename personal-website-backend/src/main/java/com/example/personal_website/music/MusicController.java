package com.example.personal_website.music;

import com.example.personal_website.users.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/beatsies")
public class MusicController {
    private final MusicService musicService;
    @Autowired
    public MusicController(MusicService musicService){this.musicService = musicService;}

    @GetMapping
    public List<Music> getAllMusic(){
        return musicService.getAllMusic();
    }
    @GetMapping(path = "/title/{title}")
    public Optional<Music> getMusicByTitle(@PathVariable String title){
        return musicService.getMusicByTitle(title);
    }
    @GetMapping(path = "/album/{album}")
    public Optional<Music> getMusicByAlbum(@PathVariable String album){
        return musicService.getMusicByAlbum(album);
    }
    @GetMapping(path = "/artist/{artist}")
    public Optional<Music> getMusicByArtist(@PathVariable String artist){return musicService.getMusicByArtist(artist);}
//    @GetMapping(path = "/users/{usersId}")
//    public List<Music> getMusicByUserId(@PathVariable Long usersId){return musicService.getMusicByUserId(usersId);}
    @GetMapping(path = "/users/{users}")
    public List<Music> getMusicByUser(@PathVariable Users users){return musicService.getMusicByUser(users);}
    @PostMapping
    public void registerMusic(@RequestBody Music music){
        musicService.addMusic(music);
    }
    @PutMapping(path = {"/{musicId}"})
    public ResponseEntity<?> updateMusic(
            @PathVariable("musicId") Long musicId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String album,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String aaUrl,
            @RequestParam(required = false) String spUrl){
        musicService.updateMusic(musicId, title, album, artist, aaUrl, spUrl);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(path = "{musicId}")
    public void deleteMusic(@PathVariable("musicId") Long musicId){musicService.deleteMusic(musicId);}
}
