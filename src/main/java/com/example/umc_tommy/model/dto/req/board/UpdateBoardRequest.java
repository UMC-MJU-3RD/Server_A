package com.example.umc_tommy.model.dto.req.board;

import com.example.umc_tommy.model.entity.board.Board;
import com.example.umc_tommy.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoardRequest {

    @NotEmpty
    private Long userId;

    @NotEmpty
    private Long boardId;

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
