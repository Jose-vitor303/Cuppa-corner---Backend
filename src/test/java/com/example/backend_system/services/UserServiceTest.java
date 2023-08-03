package com.example.backend_system.services;

import com.example.backend_system.entities.User;
import com.example.backend_system.model.UserRole;
import com.example.backend_system.repository.UserRepository;
import com.example.backend_system.repository.UserRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp(){
        userService = new UserService(userRepository);
    }


    @Test
    void canFindAll() {
        //when
        userService.findAll();

        //then
        verify(userRepository).findAll();
    }

    @Test
    void testGetUserRoleForFirstUserWithNoUsers() {
        //when
        when(userRepository.count()).thenReturn(0L);

        UserRole result = userService.getUserRoleForFirstUser();

        assertEquals(UserRole.ADMIN, result);

    }

    @Test
    public void testGetUserRoleForFirstUserWithUsers() {

        when(userRepository.count()).thenReturn(1L);
        UserRole result = userService.getUserRoleForFirstUser();

        assertEquals(UserRole.USER, result);

    }
}