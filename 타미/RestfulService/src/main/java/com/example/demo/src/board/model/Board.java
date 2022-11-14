package com.example.demo.src.board.model;

import com.example.demo.src.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // pk 생성을 데이터베이스에 위임
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    private User user;

    private String title;

    private String content;

    @Builder
    public Board(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Board updateBoard(PatchBoardReq request, User user) {
        this.user = user;
        this.title = request.getTitle();
        this.content = request.getContent();

        return this;
    }

    private boolean isDeleted = false;

    public void delete(){
        this.isDeleted = true;
    }

    public void restore(){
        this.isDeleted = false;
    }

}
