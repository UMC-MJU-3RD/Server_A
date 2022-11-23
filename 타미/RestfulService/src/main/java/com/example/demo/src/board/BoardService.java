package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.*;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.model.GetUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserProvider userProvider;

    private final UserDao userDao;
    private final BoardDao boardDao;
    private final BoardProvider boardProvider;



    public BaseResponse createBoard(PostBoardReq request) throws BaseException {
        
        GetUserRes user = userProvider.getUser(request.getUserIdx());
        
        if(user != null){
            boardDao.createBoard(request);
            return new BaseResponse("게시글 등록 성공");
        }

        return new BaseResponse(USERS_EMPTY_USER_ID);
    }

    public BaseResponse getBoardList() throws BaseException {

        List<GetBoardRes> boardList =  boardProvider.getBoardList();

        if(boardList.isEmpty())
            return new BaseResponse(EMPTY_BOARDS);

        else return new BaseResponse(boardList);


    }

    public BaseResponse updateBoard(PatchBoardReq request) throws BaseException {
        GetBoardRes board = boardProvider.getBoard(request.getBoardIdx());

        if(board != null){
            boardDao.modifyBoard(request);
            return new BaseResponse("게시글 수정 성공");
        }

        return new BaseResponse(EMPTY_BOARD_BY_BOARD_ID);
    }

    public BaseResponse deleteBoard(PatchBoardStatusReq request) throws BaseException {
        if(request.getIsDeleted() == 1)
            return new BaseResponse(HttpStatus.BAD_REQUEST, "이미 삭제된 글입니다.", HttpStatus.BAD_REQUEST.value());

        GetUserRes user = userProvider.getUser(request.getUserIdx());
        GetBoardRes board = boardProvider.getBoard(request.getBoardIdx());

        if(user == null)
            return new BaseResponse(USERS_EMPTY_USER_ID);

        if(board == null)
            return new BaseResponse((EMPTY_BOARD_BY_BOARD_ID));

        else {
            try {
                boardDao.deleteBoard(request);
                return new BaseResponse("삭제 성공");
            } catch (Exception exception) {
                throw new BaseException(DATABASE_ERROR);
            }
        }

    }
}
