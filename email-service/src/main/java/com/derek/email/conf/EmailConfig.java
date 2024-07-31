package com.derek.email.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Derek Zhu
 * @Description
 * @Date 2024/07/14:57
 */
@Data
@Component
public class EmailConfig {
    @Value("${email.isMock}")
    private boolean isMock;
}
