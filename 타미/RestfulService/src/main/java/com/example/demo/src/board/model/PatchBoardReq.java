package com.example.demo.src.board.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PatchBoardReq {

    @NotNull
    private int boardIdx;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private int isDeleted;
}
