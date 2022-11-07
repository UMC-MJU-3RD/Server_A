package com.example.umc_tommy.controller.api.board;

import com.example.umc_tommy.model.dto.DefaultRes;
import com.example.umc_tommy.model.dto.req.board.BoardRequest;
import com.example.umc_tommy.model.dto.req.board.UpdateProductRequest;
import com.example.umc_tommy.model.dto.res.board.BoardResponse;
import com.example.umc_tommy.model.dto.res.board.SimpleBoardResponse;
import com.example.umc_tommy.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시판 글 목록 조회
    @GetMapping("/boards")
    public ResponseEntity<DefaultRes<List<SimpleBoardResponse>>> getBoardList(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC, size = 8) Pageable pageable){
        return new ResponseEntity<> (boardService.getBoardList(pageable), HttpStatus.OK);
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<DefaultRes<BoardResponse>> getBoardDetail(Long boardId){
        return new ResponseEntity<>(boardService.getBoardDetail(boardId), HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<DefaultRes> createBoard(@Valid @RequestBody BoardRequest request){
        return new ResponseEntity<>(boardService.createBoard(request), HttpStatus.OK);
    }


    @PatchMapping (value = "/boards")
    public ResponseEntity<DefaultRes> updateBoard(@Valid @RequestBody BoardRequest request){
        return new ResponseEntity<>(boardService.updateBoard(request), HttpStatus.OK);
    }


    // soft-delete
    @DeleteMapping  (value = "/boards/{boardId}")
    public ResponseEntity<DefaultRes> deleteBoard(Long boardId,  @RequestParam("userId") Long userId){
        return new ResponseEntity<>(boardService.deleteBoard(boardId, userId), HttpStatus.OK);
    }
}
