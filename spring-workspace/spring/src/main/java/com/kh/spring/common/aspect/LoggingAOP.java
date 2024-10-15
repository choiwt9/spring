package com.kh.spring.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect // 해당 클래스가 aspect라는 것을 표시
@Component // 스프링이 해당 클래스(빈)를 찾을 수 있도록 표시(빈에 등록)
@EnableAspectJAutoProxy // AOP를 활성화 시켜주는 선언
public class LoggingAOP {
	/*
	 * *시점
	 * 
	 * @before : 대상 메소드 실행 전에 Advice(기능)가 실행된다.
	 * 
	 * @After : 대상 메소드 실행 후에 Advice (기능)가 실행 된다
	 * 
	 * @Around : 대상메소드를 감싸서 메소드 호출 전/후에 Advice를 실행한다
	 * 
	 * @AfterReturning : 대상 메소드가 정상적으로 변환된 후에 advice 가 실행된다.
	 * 
	 * @AfterThrowing : 대상 메소드가 예외를 던진 후에 Advice 가 실행된다.
	 */

	/*
	 * *대상
	 * 
	 *  target : 특정 인터페이스와 그 자식클래스
	 *  within : 특정 패키지 또는 클래스
	 *  execution : 표현식으로 지정
	 */
	
	//Pointcut : 기능을 사용할 지정(대상)을 정의
	  // -대상: com.kh.spring 패키지 하위 패키지 중 controller 내에 있는 모든 클래스의 모든 메소드
	@Pointcut("execution(* com.kh.spring..controller.*.*(..))")
	private void cut() {}
	//cut 메소드가 실행되기 전에 기능을 동작 => @Before
	@Before("cut()")
	public void before (JoinPoint joinPoint) {
		//JoinPoint : 프로그램 실행 중 특정 지점을 나타내고, 메소드 실행이 가장 일반적인 JoinPoint
		
		//메소드 이름 추출
		MethodSignature ms=(MethodSignature)joinPoint.getSignature();
		java.lang.reflect.Method method = ms.getMethod();
		
		// *메소드에 전달되는 매개변수 배열을 추출
		Object[] args = joinPoint.getArgs();
		
		System.out.println("==============before Method===============");
		System.out.println("information :" + ms);
		System.out.println("Method name :" + method);
		System.out.println("parameter :" + Arrays.toString(args));	
	}
	@AfterReturning(value = "cut()", returning = "obj")
	public void afterReturning(JoinPoint joinPoint, Object obj) {
		
		MethodSignature ms=(MethodSignature)joinPoint.getSignature();
		java.lang.reflect.Method method = ms.getMethod();
		
		System.out.println("=================After Return Method=================");
		System.out.println("information :" + ms);
		System.out.println("Method name :" + method);
		System.out.println("Object :" + obj);
		
	}
	
	//시간 측정 =>< 메소드 호출되기 전 측정 시작, 메소드 호출 후 시간 측정 종료
	@Around("cut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// 메소드 호출되기 전 시간 측정 시작
		long startTime = System.currentTimeMillis();
		
		Object work = pjp.proceed();  //원래 메소드가 수행할 기능
		
		//메소드 호출 후 시간 측정
		long endTime = System.currentTimeMillis();
		
		long time = endTime - startTime;
		System.out.println("---Around Method---");
		System.out.println("information :" + pjp.getSignature());
		System.out.println("proceeding time :" + time);
		
		return work;
		
	}
}
