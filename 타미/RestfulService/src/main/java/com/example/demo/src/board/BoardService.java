package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.*;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BaseResponse getBoardList() throws BaseException {
        List<Board> boardList  = boardRepository.findAll();

        if(boardList.isEmpty()){
            return new BaseResponse(EMPTY_BOARDS);
        }

        return new BaseResponse(getSimpleBoardResponseList(boardList));
    }

    private List<SimpleBoardRes> getSimpleBoardResponseList(List<Board> boardList) {
        List<SimpleBoardRes> simpleBoardResponseList = new LinkedList<>();

        for(Board board : boardList){
            User user = board.getUser();

            SimpleBoardRes simpleBoardResponse = new SimpleBoardRes(board.getId(), user.getNickname(), board.getTitle());
            simpleBoardResponseList.add(simpleBoardResponse);
        }

        return simpleBoardResponseList;
    }


    public BoardRes getBoardDetail(Long boardId) throws BaseException {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return new BoardRes(board.getId(), board.getUser().getNickname(), board.getTitle(),
                            board.getContent());
        }
        else {
            throw new BaseException(EMPTY_BOARD_BY_BOARD_ID);
        }

    }

    public BaseResponse createBoard(PostBoardReq request) throws BaseException {
        Optional<User> user = userRepository.findById(request.getUserId());

        if(user.isPresent()){
            user.get().getBoardList().add(boardRepository.save(request.toEntity(user.get())));

            return new BaseResponse("등록 성공");
        }
        else throw new BaseException(USERS_EMPTY_USER_ID);
    }

    public BaseResponse updateBoard(PatchBoardReq request) throws BaseException {
        Optional<User> user = userRepository.findById(request.getUserId());

        if(user.isPresent()){
            Optional<Board> optionalBoard = boardRepository.findById(request.getBoardId());

            if(optionalBoard.isPresent()){
                Board board = optionalBoard.get().updateBoard(request, user.get());
                boardRepository.save(board);

                return new BaseResponse("수정 성공");

            }
            throw new BaseException(EMPTY_BOARD_BY_BOARD_ID);

        }

        throw new BaseException(USERS_EMPTY_USER_ID);
    }

    public BaseResponse deleteBoard(Long boardId, int userId) throws BaseException {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if(optionalBoard.isPresent()){
            Board existingBoard = optionalBoard.get();
            int existingUserId = existingBoard.getUser().getUserIdx();

            if(existingUserId == userId){
                // soft-delete
                existingBoard.delete();
                boardRepository.save(existingBoard);

                return new BaseResponse("삭제 성공");
            }

            throw new BaseException(USERS_EMPTY_USER_ID);

        }

        throw new BaseException(EMPTY_BOARD_BY_BOARD_ID);


    }
}
