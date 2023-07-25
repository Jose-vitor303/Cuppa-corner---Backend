package com.example.backend_system.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{

    private String token;
}
