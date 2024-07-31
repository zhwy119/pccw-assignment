package com.derek.email.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.derek.common.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("email")
public class Email extends BaseEntity {
    public Email(String subject, String content, String recipientAddress){
        this.subject = subject;
        this.content = content;
        this.recipientAddress = recipientAddress;
    }
    private String subject;
    private String content;
    private String recipientAddress;
    private Long userId;
    private int sentStatus;
}
