package cn.tedu.store.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 
 * @author soft01
 *
 */
@Component
//@Aspect
public class DemoAspect {
	public DemoAspect() {
		System.out.println("创建：demoAspect");
	}
//	@Before("bean(userService)")
//	public void test() {
//		System.out.println("hello world");
//	}
//	
//	@After("bean(userService)")
//	public void test2() {
//		System.out.println("after world");
//	}
	
	@AfterReturning("bean(userService)")
	public void test3() {
		System.out.println("hello @afterReturning");
	}
	
	@AfterThrowing("bean(userService)")
	public void test4() {
		System.out.println("hello @afterThrowing");
	}
	
	@Around("bean(userService) || bean(dictService)")
	public Object test5(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("hello @around before");
		Object retval = pjp.proceed();
		Signature signature = pjp.getSignature();
		System.out.println(signature);
		System.out.println("hello @around after"+retval);
		return retval;
	}
}
