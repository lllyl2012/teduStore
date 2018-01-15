import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.AddressService;
import cn.tedu.store.service.GoodsService;
import cn.tedu.store.service.UserService;
import cn.tedu.store.service.UserServiceImpl;

public class TestService {
	AbstractApplicationContext ac;
	UserService service;
	GoodsService goodsService;
	AddressService addressService;
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("conf/spring-db.xml","conf/spring-mybatis.xml","conf/spring-service.xml");
		service = ac.getBean("userService",UserServiceImpl.class);
		goodsService = ac.getBean("goodsService",GoodsService.class);
		addressService = ac.getBean("addressService",AddressService.class);
	}
	@After
	public void destory() {
		ac.close();
	}
	@Test
	public void testFindUser() {
		User user = service.findUserByUsername("haha");
		System.out.println(user);
	}
	@Test
	public void testRegisterService() {
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123123");
		user.setEmail("222@qq.com");
		user.setPhone("222222");
		service.register(user);
	}
	@Test
	public void testGetRecord() {
		boolean flag1 = service.checkEmailExists("222@qq.com");
		boolean flag2 = service.checkPhoneExists("123123");
		System.out.println(flag1+":"+flag2);
	}
	@Test
	public void testmyPWDservice() {
		Integer num =  service.updatePassword("111111", 4, "2222222");
		System.out.println(num);
	}
	@Test
	public void testFindIsDefaultAddress() {
		Address a = addressService.findDefaultByUid(4);
		System.out.println(a);
	}
}
