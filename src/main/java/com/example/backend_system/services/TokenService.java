package com.example.backend_system.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.backend_system.config.TokenRefreshException;
import com.example.backend_system.entities.RefreshToken;
import com.example.backend_system.entities.User;
import com.example.backend_system.repository.RefreshTokenRepository;
import com.example.backend_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error JWT TOKEN", exception);
        }
    }


    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser((User) userRepository.findByLogin(user.getUsername()));
        refreshToken.setExpiryDate(getExpirationRefreshToken());
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }

    public String generateTokenFromUserName(String username){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth")
                    .withSubject(username)
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error JWT TOKEN", exception);
        }
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }


    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


    private Instant getExpirationRefreshToken(){ return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-03:00"));
    }
}
