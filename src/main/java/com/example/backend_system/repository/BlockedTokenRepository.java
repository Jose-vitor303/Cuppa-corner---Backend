package com.example.backend_system.repository;

import com.example.backend_system.entities.BlockedToken;
import com.example.backend_system.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlockedTokenRepository extends JpaRepository<BlockedToken, Long> {

    boolean existsByRefreshTokenBlocked(String refreshTokenBlocked);

    Optional<BlockedToken> findByRefreshTokenBlocked(String refreshToken);
}
