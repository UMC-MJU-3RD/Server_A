package com.example.demo.src.board.model;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardReq {

    @NotNull
    private int userIdx;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;


}
