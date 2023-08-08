package com.example.backend_system.services;


import com.example.backend_system.entities.BlockedToken;
import com.example.backend_system.entities.RefreshToken;
import com.example.backend_system.repository.BlockedTokenRepository;
import com.example.backend_system.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlockedTokenService {

    private final BlockedTokenRepository blockedTokenRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public void blockToken(Optional<RefreshToken> refreshTokenOptional) {

        if (refreshTokenOptional.isPresent()){
            RefreshToken refreshToken = refreshTokenOptional.get();
            BlockedToken blockedToken = new BlockedToken();
            blockedToken.setRefreshToken(refreshTokenRepository.findById(refreshToken.getId()));
            blockedToken.setRefreshTokenBlocked(refreshToken.getToken());
            blockedTokenRepository.save(blockedToken);
        }
    }

    public boolean isTokenBlocked(String token) {
       return blockedTokenRepository.existsByRefreshTokenBlocked(token);
    }

    public Optional<BlockedToken> findByToken(String token) {
        return blockedTokenRepository.findByRefreshTokenBlocked(token);
    }
}


