package com.example.umc_tommy.model.dto;

import lombok.Getter;

@Getter
public class ReplaceTokenResponseDto {
    private String accessToken;

    public ReplaceTokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
