package com.derek.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.derek.email.service.EmailFeignService;
import com.derek.email.vo.EmailVo;
import com.derek.user.entity.User;
import com.derek.user.mapper.UserMapper;
import com.derek.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    private final UserMapper userMapper;
    private final EmailFeignService emailFeignService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, EmailFeignService emailFeignService) {
        this.userMapper = userMapper;
        this.emailFeignService = emailFeignService;
    }

    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }
    @Async
    @Override
    public void sendEmailForUser(User user) {
        EmailVo emailVo = new EmailVo();
        emailVo.setSubject("User Registered");
        emailVo.setContent("Hello "+user.getUserName()+", welcome to gccw!");
        emailVo.setRecipientAddress(user.getEmail());
        emailVo.setUserId(user.getId());
        try {
            emailFeignService.sendEmail(emailVo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
