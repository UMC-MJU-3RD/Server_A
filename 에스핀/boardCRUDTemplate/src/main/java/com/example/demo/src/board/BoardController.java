package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.*;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/app/boards")
public class BoardController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final BoardProvider boardProvider;
    @Autowired
    private final BoardService boardService;
    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final JwtService jwtService;


    public BoardController(BoardProvider boardProvider, BoardService boardService, UserProvider userProvider, JwtService jwtService) {
        this.boardProvider = boardProvider;
        this.boardService = boardService;
        this.userProvider = userProvider;
        this.jwtService = jwtService;
    }


    /**
     * 게시물 작성 API
     * [POST] /boards
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostBoardRes> createUser(@RequestBody PostBoardReq postBoardReq) {
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //현재 접근한 유저가 존재하면 작성자 설정 후 등록
            postBoardReq.setWriter(userProvider.getUser(userIdxByJwt).getNickname());
            PostBoardRes postBoardRes = boardService.createBoard(postBoardReq);
            return new BaseResponse<>(postBoardRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 게시글 수정 API
     * [PATCH] /boards/:boardIdx
     */
    @ResponseBody
    @PatchMapping("/{boardIdx}")
    public BaseResponse<String> modifyBoard(@PathVariable int boardIdx, @RequestBody Board board) {
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //작성자의 userIdx와 접근한 유저가 같은지 확인
            if(!Objects.equals(boardProvider.getBoard(boardIdx).getWriter(), userProvider.getUser(userIdxByJwt).getNickname())){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PatchBoardReq patchBoardReq = new PatchBoardReq(boardIdx, board.getTitle(), board.getContent());
            boardService.modifyBoard(patchBoardReq);

            String result = "게시글이 수정되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 게시글 삭제 API
     * [PATCH] /boards/:boardIdx/delete
     */
    @ResponseBody
    @PatchMapping("/{boardIdx}/delete")
    public BaseResponse<String> deleteBoard(@PathVariable("boardIdx") int boardIdx){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //작성자의 userIdx와 접근한 유저가 같은지 확인
            if(!Objects.equals(boardProvider.getBoard(boardIdx).getWriter(), userProvider.getUser(userIdxByJwt).getNickname())){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PatchStatusReq patchStatusReq = new PatchStatusReq(boardIdx, "D");
            boardService.deleteBoard(patchStatusReq);

            String result = "게시글이 삭제되었습니다.";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 모든 게시글 조회 API
     * [GET] /boards? page=
     *
     * 또는
     *
     * 해당 제목을 갖는 게시글 조회 API
     * [GET] /boards? title= &page=
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetBoardRes>> getUsers(@RequestParam(required = false) String title, int page) {
        try {
            if (title == null) {
                List<GetBoardRes> getBoardsRes = boardProvider.getBoards(page);
                return new BaseResponse<>(getBoardsRes);
            }

            List<GetBoardRes> getBoardsRes = boardProvider.getBoardsByTitle(title, page);
            return new BaseResponse<>(getBoardsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 게시글 1개 조회 API
     * [GET] /boards/:boardIdx
     */
    @ResponseBody
    @GetMapping("/{boardIdx}")
    public BaseResponse<GetBoardRes> getUser(@PathVariable("boardIdx") int boardIdx) {
        try {
            GetBoardRes getUserRes = boardProvider.getBoard(boardIdx);
            return new BaseResponse<>(getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }

}
