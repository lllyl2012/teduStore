package cn.tedu.store.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {
	@Around("bean(*Service)")
	public Object testTime(ProceedingJoinPoint pjp) throws Throwable{
		try {
			long start = System.currentTimeMillis();
			Object relval=pjp.proceed();
			long end = System.currentTimeMillis();
			System.out.println(end-start);
			return relval;
		} catch (Throwable e) {
			throw e;
		}
	}
//	@Around("within(cn.tedu.store.service.*ServiceImpl)")
//	public void testTime2(ProceedingJoinPoint pjp) {
//		
//	}
//	@Around("execution(* cn.tedu.store..*ServiceImpl.*(..))")
//	public void testTime3(ProceedingJoinPoint pjp) {
//		
//	}
}
