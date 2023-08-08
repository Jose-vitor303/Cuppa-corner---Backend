package com.example.backend_system.repository;

import com.example.backend_system.entities.User;
import com.example.backend_system.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfUserLoginExists(){
        //given
        String name = "vitor";
        User user = new User(name, "12912", UserRole.ADMIN);
        userRepository.save(user);

        //when
        UserDetails expected = userRepository.findByLogin(name);

        //then
        assertThat(expected).isNotNull();
        assertThat(expected.getUsername()).isEqualTo(name);
        assertThat(expected.getPassword()).isEqualTo("12912");
    }

    @Test
    void itShouldCheckIfUserLoginDoesNotExists(){
        //given
        String name = "vitor";

        //when
        UserDetails expected = userRepository.findByLogin(name);

        //then
        assertThat(expected).isNull();
    }


}
