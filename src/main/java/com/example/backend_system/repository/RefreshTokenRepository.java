package com.example.backend_system.repository;

import com.example.backend_system.entities.RefreshToken;
import com.example.backend_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken findById(UUID uuid);

    int deleteByUser(User user);
}
