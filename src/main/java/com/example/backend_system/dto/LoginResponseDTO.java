package com.example.backend_system.dto;

import com.example.backend_system.entities.RefreshToken;

public record LoginResponseDTO(String token, String refreshToken) {
}
