package com.example.demo.src.reply.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PatchReplyStatusReq {

    @NotNull
    int replyIdx;

    @NotNull
    int userIdx;

    @NotNull
    int boardIdx;

    @NotNull
    int isDeleted;
}
