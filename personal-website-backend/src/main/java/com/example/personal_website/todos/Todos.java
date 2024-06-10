package com.example.personal_website.todos;

import com.example.personal_website.users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Todos {
    @Id
    @SequenceGenerator(
            name = "todos_sequence",
            sequenceName = "todos_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todos_sequence"
    )
    private long id;
    private String category;
    private String task;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean completed;
    //Relationship mapping with Users column
    //Determines who can see which Todos
    @ManyToOne
    @JoinColumn(name = "usersId", nullable = false)
    @JsonBackReference
    private Users users;

    //Database Constructor
    public Todos(String category, String task, String description, Users users) {
        this.category = category;
        this.task = task;
        this.description = description;
        this.users = users;
    }

    //Task creation timestamping
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
        return "Todos{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", completed=" + completed +
                ", users=" + users +
                '}';
    }
}
