package com.example.webcustomertracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.example.webcustomertracker.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.example.webcustomertracker.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.example.webcustomertracker.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>>> in @Before: calling method: " + method);

        Object[] args = joinPoint.getArgs();
        for (Object arg:args) {
            logger.info("======>>> argument: " + arg);
        }
    }
    //add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>>> in @AfterReturning: from method: " + method);

        logger.info("=====>>> result: " + result);
    }
}
