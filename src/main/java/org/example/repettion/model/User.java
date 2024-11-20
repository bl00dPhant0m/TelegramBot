package org.example.repettion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    private long chat_id;
    private String login;
    private Long countMessageCurrentDay = 0L;

}
