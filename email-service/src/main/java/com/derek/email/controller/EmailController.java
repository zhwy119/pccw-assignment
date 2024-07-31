package com.derek.email.controller;


import com.derek.common.vo.RestResult;
import com.derek.email.service.EmailService;
import com.derek.email.vo.EmailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.derek.email.entity.Email;
import java.util.List;


@RestController
@Slf4j
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping ("/sendEmail")
    public RestResult sendEmail(@RequestBody(required = true) EmailVo emailVo ) {
        log.info("start to send an email for a new user. {}",emailVo.toString());
        emailService.sendSimpleEmail(emailVo.getRecipientAddress(),emailVo.getSubject(),emailVo.getContent(),emailVo.getUserId());
        return RestResult.success();
    }

    @GetMapping ("/emails")
    public RestResult emails() {
        List<Email> emailVoList = emailService.listEmails();
        RestResult restResult = RestResult.success();
        restResult.setObject(emailVoList);
        return restResult;
    }


}
