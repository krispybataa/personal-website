package com.example.personal_website.music;

import com.example.personal_website.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    @Query("SELECT m FROM Music m WHERE m.title LIKE CONCAT('%', :title, '%')")
    Optional<Music> findMusicByTitle(String title);
    @Query("SELECT m FROM Music m WHERE m.album LIKE CONCAT('%', :album, '%')")
    Optional<Music> findMusicByAlbum(String album);
    @Query("SELECT m FROM Music m WHERE m.artist LIKE CONCAT('%', :artist, '%')")
    Optional<Music> findMusicByArtist(String artist);
    List<Music> findMusicByUsers(Users users);
    List<Music> findMusicByUsersId(Long usersId);
}
