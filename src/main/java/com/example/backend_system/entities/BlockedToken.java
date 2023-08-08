package com.example.backend_system.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;


@Getter
@Setter
@NoArgsConstructor
@Entity

public class BlockedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_refresh_token", referencedColumnName = "id")
    private RefreshToken refreshToken;

    @Column(nullable = false, unique = true)
    private String refreshTokenBlocked;

}
