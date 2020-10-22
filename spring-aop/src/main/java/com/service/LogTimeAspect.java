package com.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Component
@Slf4j
public class LogTimeAspect {
	
	@Around("@annotation(com.service.LogTime) && execution(public * * (..))")
	public Object execute(ProceedingJoinPoint pj) {
		Object value = null;
		Long start = null, end = null;
		try {
			start = System.currentTimeMillis();
			value = pj.proceed();
			end = System.currentTimeMillis();
		} catch (Throwable e) {
			end = System.currentTimeMillis();
		}
		System.out.println(pj.getSignature().getDeclaringType()+"."+pj.getSignature().getName()+" took " + (end -start) +"ms to complete");
		return value;
	}

}
