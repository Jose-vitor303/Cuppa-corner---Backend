package com.example.backend_system.dto;

import com.example.backend_system.enums.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest{
    private String name;
    private String email;
    private String password;
    private Role role;
}
