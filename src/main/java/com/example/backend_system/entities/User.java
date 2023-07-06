package com.example.backend_system.entities;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserAccount")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String name;
    private String email;
    private String password;
    private String address;
    private String photograph;


    public User(String name, String email, String password, String address, String photograph) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.photograph = photograph;
    }
}
