package com.example.backend_system.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest{

    private String email;
    private String password;
}
