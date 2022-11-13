package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class BoardProvider {

    private final BoardDao boardDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public BoardProvider(BoardDao boardDao, JwtService jwtService) {
        this.boardDao = boardDao;
        this.jwtService = jwtService;
    }

    // 게시글 전체 조회
    public List<GetBoardRes> getBoards() throws BaseException {
        try {
            List<GetBoardRes> getBoardRes = boardDao.getBoards();
            return getBoardRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 해당 title을 갖는 게시글 조회
    public List<GetBoardRes> getBoardsByTitle(String title) throws BaseException {
        try {
            List<GetBoardRes> getBoardsRes = boardDao.getBoardsByTitle(title);
            return getBoardsRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 해당 boardIdx를 갖는 게시글 조회
    public GetBoardRes getBoard(int boardIdx) throws BaseException {
        try {
            GetBoardRes getBoardRes = boardDao.getBoard(boardIdx);
            return getBoardRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
