package com.example.umc_tommy.repository.board;

import com.example.umc_tommy.model.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select board from Board board join fetch board.user left join fetch board.boardReplyList",
            countQuery = "select count(board) from Board board ")
    Page<Board> findAll(Pageable pageable);
}
