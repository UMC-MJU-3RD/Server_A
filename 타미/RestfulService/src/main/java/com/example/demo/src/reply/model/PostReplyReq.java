package com.example.demo.src.reply.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReplyReq {

    @NotEmpty
    String content;

    @NotNull
    int userIdx;

    @NotNull
    int boardIdx;
}
