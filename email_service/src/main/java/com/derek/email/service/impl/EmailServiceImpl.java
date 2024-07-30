package com.derek.email.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.derek.email.conf.EmailConfig;
import com.derek.email.entity.Email;
import com.derek.email.mapper.EmailMapper;
import com.derek.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements EmailService {

    private final EmailMapper emailMapper;
    private final JavaMailSender mailSender;
    private final EmailConfig emailConfig;

    @Autowired
    public EmailServiceImpl(EmailMapper emailMapper, JavaMailSender mailSender, EmailConfig emailConfig) {
        this.emailMapper = emailMapper;
        this.mailSender = mailSender;
        this.emailConfig = emailConfig;
    }

    @Override
    public List<Email> listEmails() {
        return emailMapper.selectAllEmails();
    }

    @Override
    public void sendSimpleEmail(String to, String subject, String content, Long userId) {
        log.info("start to send email");
        boolean sent = Boolean.FALSE;
        if (emailConfig.isMock()) {
            sent = Boolean.TRUE;
            log.info("mock email sent. to:{}, subject:{}, content:{}", to, subject, content);
        } else {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(content);
                mailSender.send(message);
                sent = Boolean.TRUE;
                log.info("email sent. to:{}, subject:{}, content:{}", to, subject, content);
            } catch (Exception e) {
                log.error("error happened while sending email to {}, for user {}", to, userId, e);
            }
        }
        Email email = new Email(subject, content, to);
        email.setSentStatus(sent ? 1 : 0);
        Date now = new Date();
        email.setCreateDate(now);
        email.setUpdateDate(now);
        email.setUserId(userId);
        this.save(email);
        log.info("email sent and saved");
    }

}
