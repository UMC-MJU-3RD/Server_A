package com.example.demo.src.reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PatchReplyReq {

    @NotNull
    int replyIdx;

    @NotNull
    int boardIdx;

    @NotEmpty
    String content;
}
