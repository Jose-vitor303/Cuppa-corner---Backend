package com.example.backend_system.controller;

import com.example.backend_system.entities.User;
import com.example.backend_system.services.UserService;
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

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();

        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(userList);
        }
    }


    @PostMapping("/add/user")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

}
