package com.derek.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.derek.user.entity.User;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface UserService extends IService<User> {
    //list all users
    public List<User> listUsers();
    @Async
    public void sendEmailForUser(User user);
}
