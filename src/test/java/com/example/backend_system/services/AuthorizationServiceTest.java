package com.example.backend_system.services;

import com.example.backend_system.entities.User;
import com.example.backend_system.model.UserRole;
import com.example.backend_system.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @Mock private UserRepository userRepository;

    private AuthorizationService authorizationService;


    @BeforeEach
    void setUp(){
        authorizationService = new AuthorizationService(userRepository);
    }

    @Test
    void loadUserByUsername() {

        String username = "igor";

        User user = new User(username, "12331", UserRole.ADMIN);

        when(userRepository.findByLogin(username)).thenReturn(user);

        UserDetails userDetails = authorizationService.loadUserByUsername(username);

        verify(userRepository).findByLogin(username);

        assertNotNull(userDetails);

        assertEquals(username, userDetails.getUsername());

    }
}