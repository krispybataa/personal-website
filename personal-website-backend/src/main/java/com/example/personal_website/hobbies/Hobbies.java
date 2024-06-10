package com.example.personal_website.hobbies;

import com.example.personal_website.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Hobbies {
    @Id
    @SequenceGenerator(
            name = "hobbies_sequence",
            sequenceName = "hobbies_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hobbies_sequence"
    )
    private long id;
    private String name;
    private String description;
    @ElementCollection
    private List<String> media;
    @ManyToOne
    @JoinColumn(name = "usersId", nullable = false)
    private Users users;

    //Database Constructor
    public Hobbies(String name, String description, List<String> media, Users users) {
        this.name = name;
        this.description = description;
        this.media = media;
        this.users = users;
    }

    public Hobbies(String name, String description, Users users) {
        this.name = name;
        this.description = description;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Hobbies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", media=" + media +
                ", users=" + users +
                '}';
    }
}
