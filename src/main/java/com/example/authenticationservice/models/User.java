package com.example.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USR_SEQ")
    @SequenceGenerator(name="USR_SEQ", sequenceName = "USR_SEQ")
    private long id;
    private String username;
    private String password;
    private String fName;
    private String lName;
}
