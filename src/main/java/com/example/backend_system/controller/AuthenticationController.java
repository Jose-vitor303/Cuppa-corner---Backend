package com.example.backend_system.controller;


import com.example.backend_system.dto.AuthenticationDTO;
import com.example.backend_system.dto.LoginResponseDTO;
import com.example.backend_system.dto.RegisterDTO;
import com.example.backend_system.entities.User;
import com.example.backend_system.model.UserRole;
import com.example.backend_system.repository.UserRepository;
import com.example.backend_system.services.AuthorizationService;
import com.example.backend_system.services.TokenService;
import com.example.backend_system.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Routes")
public class AuthenticationController {

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

        return ResponseEntity.ok(new LoginResponseDTO(token));
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
}
