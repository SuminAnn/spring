package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

	@Around("execution(* com.example.demo..*(..))") // 적용 대상을 선택하는 어노테이션
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		System.out.println("START: " + joinPoint.toString());
		try {
			return joinPoint.proceed();
		}finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
		}
		
	} // 중간에 intercept해서 풀수 있는 기술을 AOP라고 한다.

	/*
	 회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다
	 시간을 축정하는 로직을 별도의 공통 로직으로 만들었다
	 핵심 관심 사항을 깔끔하게 유지할 수 있다
	 변경이 필요하면 이 로직만 변경하면 된다
	 원하는 적용 대상을 선택할 수 있다
	 
	 AOP 적용 후 의존관계
	  - AOP적용 시 프록시(가짜)를 생성
	  - spring을 등록할때 가짜 스프링빈(프록시)을 먼저 세우고, 끝난후 진짜 스프링빈을 호출한다
	 */
}
