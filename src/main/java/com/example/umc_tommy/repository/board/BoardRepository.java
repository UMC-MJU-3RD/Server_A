package com.example.umc_tommy.repository.board;

import com.example.umc_tommy.model.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
