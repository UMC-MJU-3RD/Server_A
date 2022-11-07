package com.example.umc_tommy.model.entity.board;

import com.example.umc_tommy.model.dto.req.board.BoardRequest;
import com.example.umc_tommy.model.entity.BaseEntity;
import com.example.umc_tommy.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성을 데이터베이스에 위임
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    private User user;

    private String title;

    private String content;

    @OneToMany(mappedBy = "board" )
    private List<BoardReply> boardReplyList = new LinkedList<>();

    @Builder
    public Board(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Board updateBoard(BoardRequest request, User user) {
        this.user = user;
        this.title = request.getTitle();
        this.content = request.getContent();

        return this;
    }



}
