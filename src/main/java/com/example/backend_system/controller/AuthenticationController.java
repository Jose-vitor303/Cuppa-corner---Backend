package com.example.backend_system.controller;


import com.example.backend_system.config.TokenRefreshException;
import com.example.backend_system.dto.*;
import com.example.backend_system.entities.RefreshToken;
import com.example.backend_system.entities.User;
import com.example.backend_system.model.UserRole;
import com.example.backend_system.repository.RefreshTokenRepository;
import com.example.backend_system.repository.UserRepository;
import com.example.backend_system.services.AuthorizationService;
import com.example.backend_system.services.BlockedTokenService;
import com.example.backend_system.services.TokenService;
import com.example.backend_system.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Routes")
public class AuthenticationController {

    private final BlockedTokenService blockedTokenService;

    private final UserRepository repository;

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final TokenService tokenService;

    @Operation(summary = "Fetch One User")
    @GetMapping("/user")
    public ResponseEntity getUser(){

        Object user = this.authorizationService.getInformationUserAuthenticated();

        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Login Route")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        var refreshToken = tokenService.createRefreshToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token, refreshToken.getToken()));
    }


    @Operation(summary = "Registration route")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserRole role = userService.getUserRoleForFirstUser();
        User newUser = new User(data.login(), encryptedPassword, role);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Request a new Token")
    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestBody @Validated TokenRefreshRequest request){
        String requestRefreshToken = request.getRefreshToken();

        Boolean blockedToken = blockedTokenService.isTokenBlocked(requestRefreshToken);

        if(blockedToken){
            tokenService.findByToken(requestRefreshToken)
                    .map(tokenService::verifyBlockToken);
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Invalid token");
        }

        return tokenService.findByToken(requestRefreshToken)
                .map(tokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = tokenService.generateTokenFromUserName(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                }).orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));

        }

        @PostMapping("/logout")
        public ResponseEntity logoutUser(@RequestBody TokenRefreshRequest refreshToken){

            Optional<RefreshToken> oldRefreshToken = tokenService.findByToken(refreshToken.getRefreshToken());
            System.out.println(oldRefreshToken);
            blockedTokenService.blockToken(oldRefreshToken);

            return ResponseEntity.ok("User Logout out ");
        }
}
