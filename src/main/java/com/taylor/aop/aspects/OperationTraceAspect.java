/*
 * File: src\main\java\com\taylor\aop\aspects\OperationTraceAspect.java
 * Project: jpa
 * Created Date: Saturday, October 28th 2023, 4:10:38 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Saturday, 28th October 2023 8:18:33 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Saturday, October 28th 2023	Rui Yu		Initial version
 */

package com.taylor.aop.aspects;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taylor.aop.annotations.OperationTrace;
import com.taylor.aop.orm.entities.Trace;
import com.taylor.aop.services.TraceService;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class OperationTraceAspect {

    private final TraceService service;

    public OperationTraceAspect(TraceService service) {
        this.service = service;
    }

    @Around("@annotation(com.taylor.aop.annotations.OperationTrace)")
    public void trace(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime callingTime = LocalDateTime.now();

        // Method Information
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(
                signature.getName(), signature.getMethod().getParameterTypes());

        // Get AOP annotation properties
        OperationTrace annotationDBTrace = method.getAnnotation(OperationTrace.class);
        String action = annotationDBTrace.action();
        String traceOn = annotationDBTrace.traceOn();

        // Get request data
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        StringBuffer sb = new StringBuffer();
        
        sb.append("Request method: ").append(request.getMethod());
        sb.append("\nRequest url: ").append(request.getRequestURL().toString());
        sb.append("\nRequest client address: ").append(getClientAddr(request));

        ObjectMapper objectMapper = new ObjectMapper();
        sb.append("\nRequest body: ").append(objectMapper.writeValueAsString(joinPoint.getArgs()));
        sb.append("\nRequest at: ").append(LocalDateTime.now().toString());

        // Below is for getting return value of joint method
        Object result = joinPoint.proceed();
        sb.append("\nJoint method result: ").append(result);

        LocalDateTime returnTime = LocalDateTime.now();
        sb.append("\nProcessing time: ").append(Duration.between(callingTime, returnTime).toMillis() + "ms");

        // Store trace data into DB
        Trace newTrace = new Trace(traceOn, action, sb.toString());
        service.createTrace(newTrace);
    }

    private String getClientAddr(HttpServletRequest request) {
        String clientAddr = "";
        if (request != null) {
            // For web application which is behind a proxy server
            clientAddr = request.getHeader("X-FORWARDED-FOR");

            if (clientAddr == null || clientAddr.length() == 0 || "unknown".equalsIgnoreCase(clientAddr)) {
                clientAddr = request.getHeader("Proxy-Client-IP");
            }

            if (clientAddr == null || clientAddr.length() == 0 || "unknown".equalsIgnoreCase(clientAddr)) {
                clientAddr = request.getHeader("WL-Proxy-Client-IP");
            }

            if (clientAddr == null || clientAddr.length() == 0 || "unknown".equalsIgnoreCase(clientAddr)) {
                // For client which don't use proxy server
                clientAddr = request.getRemoteAddr();
            }
        }

        return clientAddr;
    }
}
