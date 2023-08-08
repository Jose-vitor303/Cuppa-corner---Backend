package com.example.backend_system.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenRefreshResponse {
    private String acessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public TokenRefreshResponse(String acessToken, String refreshToken){
        this.acessToken = acessToken;
        this.refreshToken = refreshToken;
    }
}
