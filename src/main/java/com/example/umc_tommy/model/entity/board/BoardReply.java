package com.example.umc_tommy.model.entity.board;

import com.example.umc_tommy.model.entity.BaseEntity;
import com.example.umc_tommy.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BoardReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성을 데이터베이스에 위임
    @Column(name = "board_reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch =FetchType.LAZY)
    @JsonIgnore
    private Board board;

    @Basic(fetch = FetchType.EAGER)
    private String comment;

    @Builder
    public BoardReply(User user, Board board, String comment) {
        this.user = user;
        this.board = board;
        this.comment = comment;
    }

   // public BoardReply updateBoard(UpdateBoardReplyRequest request) {
   //     this.comment = request.getComment();
   //     return this;
   // }
}
