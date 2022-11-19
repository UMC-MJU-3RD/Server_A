package com.example.demo.src.reply;

import com.example.demo.config.BaseException;
import com.example.demo.src.board.BoardDao;
import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.src.reply.model.GetReplyRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class ReplyProvider {

    private final ReplyDao replyDao;

    // 게시글 내의 댓글 목록 전체 조회
    public List<GetReplyRes> getReplyList(int boardIdx) throws BaseException {
        try {
            List<GetReplyRes> getReplyResList = replyDao.getReplyList(boardIdx);
            return getReplyResList;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
