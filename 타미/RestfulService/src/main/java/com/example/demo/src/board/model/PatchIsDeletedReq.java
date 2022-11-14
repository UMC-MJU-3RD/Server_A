package com.example.demo.src.board.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchIsDeletedReq {

    @NotEmpty
    private int boardIdx;

    private boolean is_deleted;
}
