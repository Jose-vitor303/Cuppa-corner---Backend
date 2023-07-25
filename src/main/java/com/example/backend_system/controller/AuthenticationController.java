package com.example.backend_system.controller;

import com.example.backend_system.dto.AuthenticationRequest;
import com.example.backend_system.dto.AuthenticationResponse;
import com.example.backend_system.dto.RegisterRequest;
import com.example.backend_system.entities.User;
import com.example.backend_system.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = authenticationService.findAll();

        if(userList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }






}
