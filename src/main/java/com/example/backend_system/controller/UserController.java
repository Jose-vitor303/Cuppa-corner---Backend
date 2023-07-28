package com.example.backend_system.controller;

import com.example.backend_system.entities.User;
import com.example.backend_system.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/teste")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.findAll();

        if(userList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }
    }
}
