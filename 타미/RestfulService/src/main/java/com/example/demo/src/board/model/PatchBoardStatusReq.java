package com.example.demo.src.board.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchBoardStatusReq {

    @NotNull
    private int userIdx;

    @NotNull
    private int boardIdx;

    @NotNull
    private int isDeleted;
}
