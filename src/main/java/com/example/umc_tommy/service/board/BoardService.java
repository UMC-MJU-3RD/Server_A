package com.example.umc_tommy.service.board;

import com.example.umc_tommy.model.dto.DefaultRes;
import com.example.umc_tommy.model.dto.req.board.BoardRequest;
import com.example.umc_tommy.model.dto.req.board.UpdateBoardRequest;
import com.example.umc_tommy.model.dto.res.board.BoardResponse;
import com.example.umc_tommy.model.dto.res.board.SimpleBoardResponse;
import com.example.umc_tommy.model.entity.board.Board;
import com.example.umc_tommy.model.entity.user.User;
import com.example.umc_tommy.repository.board.BoardRepository;
import com.example.umc_tommy.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public DefaultRes<List<SimpleBoardResponse>> getBoardList( ) {
        List<Board> boardList = boardRepository.findAll();

        if (boardList.isEmpty()) {
            return DefaultRes.response(HttpStatus.OK.value(),"데이터 없음");
        }

        return DefaultRes.response(HttpStatus.OK.value(),"조회 성공", getSimpleBoardResponseList(boardList));
    }

    @NotNull
    private List<SimpleBoardResponse> getSimpleBoardResponseList(List<Board> boardList) {
        List<SimpleBoardResponse> simpleBoardResponseList = new LinkedList<>();

        for(Board board : boardList){
            User user = board.getUser();

            SimpleBoardResponse simpleBoardResponse = new SimpleBoardResponse(board.getId(), user.getNickName(), board.getTitle(), board.getCreatedAt(), board.getModifiedAt());
            simpleBoardResponseList.add(simpleBoardResponse);
        }

        return simpleBoardResponseList;
    }


    public DefaultRes<BoardResponse> getBoardDetail(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return DefaultRes.response(HttpStatus.OK.value(), "조회 성공",
                    new BoardResponse(board.getId(), board.getUser().getNickName(), board.getTitle(),
                            board.getContent(), board.getCreatedAt(), board.getModifiedAt()));
        }
        else {
            return DefaultRes.response(HttpStatus.OK.value(), "데이터 없음");
        }

    }

    public DefaultRes createBoard(BoardRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());

        if(user.isPresent()){
            user.get().getBoardList().add(boardRepository.save(request.toEntity(user.get())));

            return DefaultRes.response(HttpStatus.OK.value(), "등록 성공");
        }
        else return DefaultRes.response(HttpStatus.OK.value(), "등록 실패(사용자 정보 없음)");
    }

    public DefaultRes updateBoard(UpdateBoardRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());

        if(user.isPresent()){
            Optional<Board> optionalBoard = boardRepository.findById(request.getBoardId());

            if(optionalBoard.isPresent()){
                Board board = optionalBoard.get().updateBoard(request, user.get());
                boardRepository.save(board);

                return DefaultRes.response(HttpStatus.OK.value(), "수정 성공");
            }

            else return DefaultRes.response(HttpStatus.OK.value(), "수정 실패(게시글 정보 없음)");
        }

        else return DefaultRes.response(HttpStatus.OK.value(), "수정 실패(사용자 정보 없음)");
    }

    public DefaultRes deleteBoard(Long boardId, long userId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(optionalBoard.isPresent()){
            Board existingBoard = optionalBoard.get();
            Long existingUserId = existingBoard.getUser().getId();

            if(existingUserId.equals(userId)){
                // soft-delete
                existingBoard.delete();
                boardRepository.save(existingBoard);

                return DefaultRes.response(HttpStatus.OK.value(), "삭제 성공");
            }

            else return DefaultRes.response(HttpStatus.OK.value(), "삭제 실패(사용자 정보 없음)");
        }
        else return DefaultRes.response(HttpStatus.OK.value(), "삭제 실패(게시글 정보 없음)");
    }
}
