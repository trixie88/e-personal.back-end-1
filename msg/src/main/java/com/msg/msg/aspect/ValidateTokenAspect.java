package com.msg.msg.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.msg.msg.service.TokenService;

@Aspect
@Configuration
public class ValidateTokenAspect {

	@Autowired
	public TokenService tokenService;

	@Pointcut("execution(* com.msg.msg.controllers.*.*(..))")
	private void pointCutForAllControllers() {
	}

	@Before("pointCutForAllControllers()")
	private void adviceForValidationOfToken(JoinPoint theJoinPoint) {
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method called " + methodSig);
		String[] parameterNames = methodSig.getParameterNames();
		Object[] arguments = theJoinPoint.getArgs();
		for (int i = 0; i < parameterNames.length; i++) {
			if (parameterNames[i].equals("tokenAlphanumeric")) {
				String tokenAlphanumeric = (String) arguments[i];
				tokenService.validateToken(tokenAlphanumeric);
			}
		}
	}
}
