package org.example.repettion.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private LocalDate dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Task(String message) {
        this.message = message;
        dateOfCreation = LocalDate.now();
    }
}
