package com.keiyam.spring_backend.controller.aspect;

import com.keiyam.spring_backend.metrics.RequestMetrics;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Order(2)
@RequiredArgsConstructor
@Component
public class ControllerMetricsAspect {
    private final RequestMetrics requestMetrics;

    @Around("ControllerPointcuts.restControllerClasses()")
    public Object measureControllerExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String controllerName = joinPoint.getTarget().getClass().getSimpleName();

        // increment request count
        requestMetrics.incrementRequestCount(controllerName, methodName);

        long startTime = System.nanoTime();

        try {
            Object result = joinPoint.proceed();

            // if ResponseEntity, record success and response size
            if (result instanceof ResponseEntity<?> response) {
                requestMetrics.recordSuccess(controllerName, methodName);
                if (response.getBody() != null) {
                    requestMetrics.recordResponseSize(
                            controllerName,
                            methodName,
                            response.getBody().toString().length()
                    );
                }
            }
            return result;
        } catch (Exception ex) {
            // record failure
            requestMetrics.recordFailure(
                    controllerName,
                    methodName,
                    ex.getClass().getSimpleName()
            );
            throw ex;
        } finally {
            // record execution time
            long duration = System.nanoTime() - startTime;
            requestMetrics.recordRequestDuration(
                    duration,
                    TimeUnit.NANOSECONDS,
                    controllerName,
                    methodName
            );
        }
    }
}
