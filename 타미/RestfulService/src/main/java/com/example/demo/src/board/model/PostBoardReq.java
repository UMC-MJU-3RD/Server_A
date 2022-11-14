package com.example.demo.src.board.model;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardReq {

    @NotEmpty
    private int userIdx;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;


}
