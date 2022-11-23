package com.example.demo.src.board;


import com.example.demo.config.BaseException;
import com.example.demo.src.board.model.PatchBoardReq;
import com.example.demo.src.board.model.PatchStatusReq;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class BoardService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BoardDao boardDao;
    private final BoardProvider boardProvider;
    private final JwtService jwtService;


    @Autowired
    public BoardService(BoardDao boardDao, BoardProvider boardProvider, JwtService jwtService) {
        this.boardDao = boardDao;
        this.boardProvider = boardProvider;
        this.jwtService = jwtService;

    }

    // 게시글 등록
    @Transactional
    public PostBoardRes createBoard(PostBoardReq postBoardReq) throws BaseException {
        if (postBoardReq.getTitle().equals("") || postBoardReq.getTitle() == null) {
            throw new BaseException(POST_BOARDS_EMPTY_TITLE);
        } else if(postBoardReq.getContent().equals("") || postBoardReq.getContent() == null){
            throw new BaseException(POST_BOARDS_EMPTY_CONTENT);
        }
        try {
            int boardIdx = boardDao.createBoard(postBoardReq);
            return new PostBoardRes(boardIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 게시글 수정(Patch)
    @Transactional
    public void modifyBoard(PatchBoardReq patchUserReq) throws BaseException {
        try {
            int result = boardDao.modifyBoard(patchUserReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_BOARD);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 게시글 삭제(Patch)
    @Transactional
    public void deleteBoard(PatchStatusReq patchStatusReq) throws BaseException {
        try {
            int result = boardDao.deleteBoard(patchStatusReq);
            if (result == 0) {
                throw new BaseException(DELETE_FAIL_BOARD);
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
