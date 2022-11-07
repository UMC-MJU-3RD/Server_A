package com.example.umc_tommy.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {

    GRATEFUL("GRATEFUL","고마운분"),
    PRECIOUS("precious","귀한분"),
    MORE_PRECIOUS("MORE_PRECIOUS","더귀한분"),
    SOUL_MATE("SOUL_MATE","천생연분");

    private final String key;
    private final String title;
}
