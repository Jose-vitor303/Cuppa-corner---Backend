package com.example.backend_system.services;


import com.example.backend_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public Object getInformationUserAuthenticated(){

        Object userAuthenticated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name;
        if(userAuthenticated instanceof UserDetails){
            name = ((UserDetails)userAuthenticated).getUsername();
        }
        return userAuthenticated;
    }

}
