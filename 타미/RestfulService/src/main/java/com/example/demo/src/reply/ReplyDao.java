package com.example.demo.src.reply;

import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.src.reply.model.GetReplyRes;
import com.example.demo.src.reply.model.PatchReplyReq;
import com.example.demo.src.reply.model.PatchReplyStatusReq;
import com.example.demo.src.reply.model.PostReplyReq;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ReplyDao {
    private final JdbcTemplate jdbcTemplate;

    // 댓글 작성
    public int createReply(PostReplyReq postReplyReq) {
        String createReplyQuery = "insert into Reply (content, userIdx, boardIdx) VALUES (?,?,?)";
        Object[] createReplyParams = new Object[]{postReplyReq.getContent(), postReplyReq.getUserIdx(), postReplyReq.getBoardIdx()};
        this.jdbcTemplate.update(createReplyQuery, createReplyParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    // 댓글 수정
    public int modifyReply(PatchReplyReq patchReplyReq) {
        String modifyReplyQuery = "update Reply set content = ? where replyIdx = ? ";
        Object[] modifyReplyParams = new Object[]{patchReplyReq.getContent(), patchReplyReq.getReplyIdx()};

        return this.jdbcTemplate.update(modifyReplyQuery, modifyReplyParams);
    }

    // 댓글 삭제
    public int deleteBoard(PatchReplyStatusReq patchReplyStatusReq) {
        String modifyBoardQuery = "update Reply set isDeleted = 1 where replyIdx = ?";
        Object[] modifyBoardParams = new Object[]{patchReplyStatusReq.getReplyIdx()};

        return this.jdbcTemplate.update(modifyBoardQuery, modifyBoardParams);
    }

    // 게시판 내의 댓글 목록 전체 조회
    public List<GetReplyRes> getReplyList(int boardIdx) {
        String getBoardsQuery = "select replyIdx, content, nickname, Reply.createdAt, Reply.updatedAt from Reply join User where isDeleted = 0 and boardIdx =" + boardIdx ;
        return this.jdbcTemplate.query(getBoardsQuery,
                (rs, rowNum) -> new GetReplyRes(
                        rs.getInt("replyIdx"),
                        rs.getString("content"),
                        rs.getString("nickname"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime())
        );
    }
}
