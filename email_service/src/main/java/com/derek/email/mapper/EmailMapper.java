package com.derek.email.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.derek.email.entity.Email;
import java.util.List;


/**
 * user mapper
 */
public interface EmailMapper extends BaseMapper<Email> {


    List<Email> selectAllEmails();
}
