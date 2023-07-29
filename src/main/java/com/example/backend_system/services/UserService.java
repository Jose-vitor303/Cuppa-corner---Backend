package com.example.backend_system.services;

import com.example.backend_system.entities.User;
import com.example.backend_system.model.UserRole;
import com.example.backend_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public UserRole getUserRoleForFirstUser() {
        long numberOfUsers = userRepository.count();
        return numberOfUsers == 0 ? UserRole.ADMIN : UserRole.USER;
    }
}
