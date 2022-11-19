package com.example.demo.src.reply.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReplyReq {

    String content;
    int userIdx;
    int boardIdx;
}
