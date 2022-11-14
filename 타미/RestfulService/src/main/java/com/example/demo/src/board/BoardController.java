package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.BoardRes;
import com.example.demo.src.board.model.PatchBoardReq;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.SimpleBoardRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

//    // 게시판 글 목록 조회
//    @GetMapping("/boards")
//    public BaseResponse getBoardList() throws BaseException {
//        return boardService.getBoardList();
//    }
//
//    @GetMapping("/boards/detail")
//    public BaseResponse<BoardRes> getBoardDetail(@RequestParam Long boardId) throws BaseException {
//        return new BaseResponse<>(boardService.getBoardDetail(boardId));
//    }

    @PostMapping("/boards")
    public BaseResponse createBoard(@RequestBody PostBoardReq request) throws BaseException {
        return boardService.createBoard(request);
    }


//    @PatchMapping (value = "/boards")
//    public BaseResponse updateBoard(@RequestBody PatchBoardReq request) throws BaseException {
//        return new BaseResponse(boardService.updateBoard(request));
//    }
//
//
//    // soft-delete
//    @DeleteMapping (value = "/boards")
//    public BaseResponse deleteBoard(@RequestParam Long boardId, @RequestParam int userId) throws BaseException {
//        return new BaseResponse(boardService.deleteBoard(boardId, userId));
//    }
}
