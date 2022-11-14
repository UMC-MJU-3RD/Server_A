package com.example.demo.src.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetBoardRes {

    private int boardIdx;
    private String title;
    private String content;
    private String nickname;
    private boolean is_deleted;

}
