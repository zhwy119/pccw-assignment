package com.derek.user.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * log the params and result using aop
 *
 */

@Component
@Aspect
@Slf4j
public class LogAdvice {

    //定义切面
    @Pointcut(value = "execution(* com.derek.user.controller.*.*(..))")
    public void logPointCut(){

    }


    @Around("logPointCut()")
    public Object logger(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();

        Object[] args = pjp.getArgs();

        ObjectMapper objMapper = new ObjectMapper();

        log.info("aop log -- 调用前: {} : {} 传递参数为: {}", className, methodName, objMapper.writeValueAsString(args));

        Object obj = pjp.proceed();

        log.info("aop log -- 调用后: {} : {} 返回值: {}", className, methodName, objMapper.writeValueAsString(obj));

        return obj;

    }


}
