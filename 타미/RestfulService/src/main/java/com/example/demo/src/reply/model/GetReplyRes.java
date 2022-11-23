package com.example.demo.src.reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetReplyRes {
    int replyIdx;
    String content;
    String nickname;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
