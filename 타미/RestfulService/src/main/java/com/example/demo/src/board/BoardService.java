package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.model.GetUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final UserProvider userProvider;

    private final UserDao userDao;
    private final BoardDao boardDao;


    public BaseResponse createBoard(PostBoardReq request) {
        try {
            GetUserRes user = userDao.getUser(request.getUserIdx());
            if(user == null){
                return new BaseResponse(USERS_EMPTY_USER_ID);
            }
            else{
                boardDao.createBoard(request);
                return new BaseResponse("게시글 등록 성공");
            }

        } catch (Exception exception) {
            return new BaseResponse(DATABASE_ERROR);
        }
    }

}
