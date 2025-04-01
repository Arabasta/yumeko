package com.keiyam.spring_backend.controller.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Slf4j
@Component
public class ControllerLoggingAspect {

    @Before("ControllerPointcuts.restControllerClasses()")
    public void logControllerEntry(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Entering {}.{}() with args: {}", className, methodName, args);
    }

    @AfterReturning(pointcut = "ControllerPointcuts.restControllerClasses()", returning = "result")
    public void logControllerExit(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.info("Exiting {}.{}() with result: {}", className, methodName, result);
    }

    @AfterThrowing(pointcut = "ControllerPointcuts.restControllerClasses()", throwing = "ex")
    public void logControllerException(JoinPoint joinPoint, Exception ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.error("Exception in {}.{}(): {}", className, methodName, ex.getMessage(), ex);
    }
}
