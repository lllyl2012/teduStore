package cn.tedu.store.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class PointCutAspect {
//	@Before("bean(userService) || bean(dictService)")
//	public void test() {
//		System.out.println("hello world");
//	}
	@Before("within(cn.tedu.store.service.UserServiceImpl)")
	public void test() {
		System.out.println("hello");
	}
//	@After("bean(*Service)")
//	public void test2() {
//		System.out.println("gaga");
//	}
	@After("within(cn.tedu.store..*Impl)")
	public void test3() {
		System.out.println("haha");
	}
}
