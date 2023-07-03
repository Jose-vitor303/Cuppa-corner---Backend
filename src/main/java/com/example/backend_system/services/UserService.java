package com.example.backend_system.services;

import com.example.backend_system.entities.User;
import com.example.backend_system.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(@RequestBody User user){
        User user1 = new User(user.getName(), user.getEmail(), user.getPassword(), user.getAddress(), user.getPhotograph());
        userRepository.save(user1);

    }


}
