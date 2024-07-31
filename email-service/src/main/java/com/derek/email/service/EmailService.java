package com.derek.email.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.derek.email.entity.Email;

import java.util.List;

public interface EmailService extends IService<Email> {
    public List<Email> listEmails();

    void sendSimpleEmail(String to, String subject, String content, Long userId);
}
