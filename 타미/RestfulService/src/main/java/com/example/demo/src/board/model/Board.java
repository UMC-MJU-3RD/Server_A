package com.example.demo.src.board.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class Board {

    private Long id;

    private int userIdx;

    private String title;

    private String content;

    private int is_deleted;

}
