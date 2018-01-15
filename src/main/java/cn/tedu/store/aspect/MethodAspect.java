package cn.tedu.store.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class MethodAspect {
	@Before("execution(* cn.tedu.store..*Service.get*(..))")
	public void test() {
		System.out.println("ga");
	}
}
