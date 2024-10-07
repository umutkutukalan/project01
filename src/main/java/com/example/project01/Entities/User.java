package com.example.project01.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    Long id;
    String username;
    String password;
}
