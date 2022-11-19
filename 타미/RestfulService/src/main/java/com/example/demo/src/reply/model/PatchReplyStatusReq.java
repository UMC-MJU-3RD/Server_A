package com.example.demo.src.reply.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchReplyStatusReq {
    int replyIdx;
    int isDeleted;
}
