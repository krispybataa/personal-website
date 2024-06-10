package com.example.personal_website.music;

import com.example.personal_website.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MusicService {
    private final MusicRepository musicRepository;

    @Autowired
    public MusicService(MusicRepository musicRepository) {this.musicRepository = musicRepository;}
    public List<Music> getAllMusic() {
        //NOTE DIFF METHODS
        return musicRepository.findAll();
    }
    //Search by Title
    public Optional<Music> getMusicByTitle(String title){
        return musicRepository.findMusicByTitle(title);
    }
    //Search by Album
    public Optional<Music> getMusicByAlbum(String album){
        return musicRepository.findMusicByAlbum(album);
    }
    //Search by Artist
    public Optional<Music> getMusicByArtist(String artist){
        return musicRepository.findMusicByArtist(artist);
    }
    //Search by User
    public List<Music> getMusicByUser(Users users){return musicRepository.findMusicByUsers(users);}
    //Search by User ID
//    public List<Music> getMusicByUserId(Long usersId) {return musicRepository.findMusicByUsersId(usersId);}

    //Method to add tracks
    public void addMusic(Music music) {
        Optional<Music> musicOptional = musicRepository.findMusicByTitle(music.getTitle());
        if(musicOptional.isPresent()){
            throw new IllegalStateException("Track already added.");
        }
        musicRepository.save(music);
    }

    public void updateMusic(Long musicId,
                            String title,
                            String album,
                            String artist,
                            String aaUrl)
    {
        Music music = musicRepository.findById(musicId).orElseThrow(()
                -> new IllegalStateException("Track ID: " + musicId + " does not exist."));

        //Update recorded title entry
        if(title != null && !title.isEmpty() && !Objects.equals(music.getTitle(), title)){
            Optional<Music> musicOptional = musicRepository.findMusicByTitle(title);
            if(musicOptional.isPresent()){
                throw new IllegalStateException("Track: " + title +" is already on record.");
            }
            music.setTitle(title);
        }

        //Update recorded album entry
        if(album != null && !album.isEmpty() && !Objects.equals(music.getAlbum(), album)){
            music.setAlbum(album);
        }

        //Update recorded artist entry
        if(artist != null && !artist.isEmpty() && !Objects.equals(music.getArtist(), artist)){
            music.setArtist(artist);
        }

        //Update recorded album URL
        if (aaUrl != null && !aaUrl.isEmpty()) {
            music.setAaUrl(aaUrl);
        }
        musicRepository.save(music);
    }

    public void deleteMusic(Long musicId) {
        boolean exists = musicRepository.existsById(musicId);
        if(!exists){
            throw new IllegalStateException("Track ID: " + musicId + " does not exist,");
        }
        musicRepository.deleteById(musicId);
    }
}
