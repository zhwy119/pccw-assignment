package com.derek.email.service;

import com.derek.common.vo.RestResult;
import com.derek.email.vo.EmailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Derek Zhu
 * @Description
 * @Date 2024/07/19:11
 */
@FeignClient(value = "gccw-assignment-email-service")
public interface EmailFeignService {
    @RequestMapping("/sendEmail")
    RestResult sendEmail(@RequestBody(required = true) EmailVo emailVo) throws Exception;
}
