package com.derek.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.derek.user.entity.User;

import java.util.List;


/**
 * user mapper
 */
public interface UserMapper extends BaseMapper<User> {


    List<User> listUsers();
}
