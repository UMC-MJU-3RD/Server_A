package com.example.demo.src.board.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class PatchBoardReq {

    @NotEmpty
    private int boardIdx;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
