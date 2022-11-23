package com.example.demo.src.reply;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.BoardDao;
import com.example.demo.src.board.BoardProvider;
import com.example.demo.src.board.model.*;
import com.example.demo.src.reply.model.GetReplyRes;
import com.example.demo.src.reply.model.PatchReplyReq;
import com.example.demo.src.reply.model.PatchReplyStatusReq;
import com.example.demo.src.reply.model.PostReplyReq;
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
public class ReplyService {

    private final UserProvider userProvider;

    private final UserDao userDao;
    private final ReplyDao replyDao;
    private final ReplyProvider replyProvider;
    private final BoardProvider boardProvider;

    // 댓글 작성 기능
    public BaseResponse createReply(PostReplyReq request) throws BaseException {
        GetUserRes user = userProvider.getUser(request.getUserIdx());
        GetBoardRes board = boardProvider.getBoard(request.getBoardIdx());

        if(user == null)
            return new BaseResponse(USERS_EMPTY_USER_ID);

        if(board == null)
            return new BaseResponse((EMPTY_BOARD_BY_BOARD_ID));

        else {
            try {
                replyDao.createReply(request);
                return new BaseResponse("댓글 등록 성공");
            } catch (Exception exception) {
                throw new BaseException(DATABASE_ERROR);
            }
        }
    }

    // 게시글 내 전체 댓글 목록 조회 기능
    public BaseResponse getReplyList(int boardIdx) throws BaseException {

        List<GetReplyRes> replyResList =  replyProvider.getReplyList(boardIdx);

        if(replyResList.isEmpty())
            return new BaseResponse(EMPTY_REPLIES);

        else return new BaseResponse(replyResList);
    }

    // 댓글 수정 기능
    public BaseResponse updateReply(PatchReplyReq request) throws BaseException {
        GetBoardRes board = boardProvider.getBoard(request.getBoardIdx());

        if(board != null){
            replyDao.modifyReply(request);
            return new BaseResponse("댓글 수정 성공");
        }

        return new BaseResponse(EMPTY_BOARD_BY_BOARD_ID);
    }

    public BaseResponse deleteReply(PatchReplyStatusReq request) throws BaseException {
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
                replyDao.deleteBoard(request);
                return new BaseResponse("삭제 성공");
            } catch (Exception exception) {
                throw new BaseException(DATABASE_ERROR);
            }
        }

    }
}
