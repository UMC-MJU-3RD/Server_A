package com.example.demo.src.reply;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.BoardService;
import com.example.demo.src.board.model.PatchBoardReq;
import com.example.demo.src.board.model.PatchBoardStatusReq;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.reply.model.PatchReplyReq;
import com.example.demo.src.reply.model.PatchReplyStatusReq;
import com.example.demo.src.reply.model.PostReplyReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    // 게시판 내 댓글 목록 조회
    @GetMapping("/replies/{boardIdx}")
    public BaseResponse getReplyList(@PathVariable int boardIdx) throws BaseException {
        return replyService.getReplyList(boardIdx);
    }

    // 게시글 등록
    @PostMapping("/replies")
    public BaseResponse createReply(@Valid @RequestBody PostReplyReq request) throws BaseException {
        return replyService.createReply(request);
    }


    @PatchMapping (value = "/replies")
    public BaseResponse updateReply(@Valid @RequestBody PatchReplyReq request) throws BaseException {
        return replyService.updateReply(request);
    }


    // soft-delete
    @DeleteMapping (value = "/replies")
    public BaseResponse deleteReply(@Valid @RequestBody PatchReplyStatusReq request) throws BaseException {
        return replyService.deleteReply(request);
    }
}
