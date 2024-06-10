package com.example.personal_website.users;

import com.example.personal_website.hobbies.Hobbies;
import com.example.personal_website.music.Music;
import com.example.personal_website.todos.Todos;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;
    private String name;
    private String username;
    private LocalDate dob;
    @Transient
    private int age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //Relationship mapping with Todos table
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Todos> todos;
    //Relationship mapping with Music
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Music> music;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Hobbies> hobbies;


    //Database Constructor
    public Users(String name, String username, LocalDate dob) {
        this.name = name;
        this.username = username;
        this.dob = dob;
    }

    //Age Calculation
    public Integer getAge(){
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    //Account Creation Timestamping
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
