package com.example.umc_tommy.model.dto.res.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleBoardResponse {

    private Long boardId;

    private String nickname;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


}
