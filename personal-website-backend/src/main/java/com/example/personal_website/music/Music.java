package com.example.personal_website.music;

import com.example.personal_website.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Music {
    @Id
    @SequenceGenerator(
            name = "music_sequence",
            sequenceName = "music_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "music_sequence"
    )
    private long id;
    private String title;
    private String album;
    private String artist;
    private String aaUrl;
    //Relationship mapping with Users column
    //Determines who can see which Todos

    @ManyToOne
    @JoinColumn(name = "usersId", nullable = false)
    private Users users;

    //Database constructor
    public Music(String title, String album, String artist, String aaUrl, Users users) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.aaUrl = aaUrl;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", aaUrl='" + aaUrl + '\'' +
                ", users=" + users +
                '}';
    }
}
