package com.example.demo.src.board;

import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.src.board.model.PatchBoardReq;
import com.example.demo.src.board.model.PatchIsDeletedReq;
import com.example.demo.src.board.model.PostBoardReq;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class BoardDao {
    private final JdbcTemplate jdbcTemplate;

    // 게시글 등록
    public int createBoard(PostBoardReq postBoardReq) {
        String createUserQuery = "insert into Board (userIdx, title, content) VALUES (?,?,?)";
        Object[] createUserParams = new Object[]{postBoardReq.getUserIdx(), postBoardReq.getTitle(), postBoardReq.getContent()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    // 게시글 수정
    public int modifyBoard(PatchBoardReq patchBoardReq) {
        String modifyBoardQuery = "update Board set title = ?, content = ? where boardIdx = ? ";
        Object[] modifyBoardParams = new Object[]{patchBoardReq.getTitle(), patchBoardReq.getContent(), patchBoardReq.getBoardIdx()};

        return this.jdbcTemplate.update(modifyBoardQuery, modifyBoardParams);
    }

    // 게시글 삭제
    public int deleteBoard(PatchIsDeletedReq patchIsDeletedReq) {
        String modifyBoardQuery = "update Board set isDeleted = ? where boardIdx = ? ";
        Object[] modifyBoardParams = new Object[]{patchIsDeletedReq.is_deleted(), patchIsDeletedReq.getBoardIdx()};

        return this.jdbcTemplate.update(modifyBoardQuery, modifyBoardParams);
    }

    // 게시판 내의 게시글 목록 전체 조회
    public List<GetBoardRes> getBoards() {
        String getBoardsQuery = "select * from Board";
        return this.jdbcTemplate.query(getBoardsQuery,
                (rs, rowNum) -> new GetBoardRes(
                        rs.getInt("boardIdx"),
                        rs.getString("nickname"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getBoolean("isDeleted"))
        );
    }

    // 해당 title을 갖는 게시글 조회
    public List<GetBoardRes> getBoardsByTitle(String title) {
        String getUsersByTitleQuery = "select * from Board where title like ? and isDeleted=0";
        String getUsersByTitleParams = "%"+title+"%";
        return this.jdbcTemplate.query(getUsersByTitleQuery,
                (rs, rowNum) -> new GetBoardRes(
                        rs.getInt("boardIdx"),
                        rs.getString("nickname"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getBoolean("isDeleted")),
                getUsersByTitleParams);
    }

    // 해당 boardIdx를 갖는 게시글 조회
    public GetBoardRes getBoard(int boardIdx) {
        String getBoardQuery = "select * from Board where boardIdx = ? and isDeleted=0";
        int getBoardParams = boardIdx;
        return this.jdbcTemplate.queryForObject(getBoardQuery,
                (rs, rowNum) -> new GetBoardRes(
                        rs.getInt("boardIdx"),
                        rs.getString("nickname"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getBoolean("isDeleted")),
                getBoardParams);
    }
}
