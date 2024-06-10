package com.example.personal_website.music;

import com.example.personal_website.users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String spUrl;
    //Relationship mapping with Users column
    //Determines who can see which Todos

    @ManyToOne
    @JoinColumn(name = "usersId", nullable = false)
    @JsonBackReference
    private Users users;

    //Database constructor
    public Music(String title, String album, String artist, String aaUrl, String spUrl, Users users) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.aaUrl = aaUrl;
        this.spUrl = spUrl;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", aaUrl='" + aaUrl + '\'' +
                ", spUrl='" + spUrl + '\'' +
                ", users=" + users +
                '}';
    }
}
