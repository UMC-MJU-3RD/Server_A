package com.example.demo.src.board.model;

import com.example.demo.src.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardReq {
    @NotEmpty
    private int userId;

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;



    @Builder
    public Board toEntity(User user){
        return Board.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
