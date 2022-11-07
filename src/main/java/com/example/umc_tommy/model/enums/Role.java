package com.example.umc_tommy.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST","고객"),
    USER("ROLE_OWNER","사장");

    private final String key;
    private final String title;


}
