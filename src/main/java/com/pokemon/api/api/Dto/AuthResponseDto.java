package com.pokemon.api.api.Dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String tokenType="Bearer";
    private String accessToken;
public AuthResponseDto(String tokenType) {
    this.tokenType = tokenType;
    this.accessToken = accessToken;
}
}
