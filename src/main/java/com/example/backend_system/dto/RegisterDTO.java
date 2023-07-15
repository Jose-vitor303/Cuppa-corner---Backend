package com.example.backend_system.dto;

import com.example.backend_system.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
