import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.entity.User;
import cn.tedu.store.entity.dict.Province;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.DictMapper;
import cn.tedu.store.mapper.OrderItemMapper;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.mapper.UserMapper;

public class Tester {
	AbstractApplicationContext ac;
	UserMapper userMapper;
	DictMapper dictMapper;
	AddressMapper addressMapper;
	OrderMapper orderMapper;
	OrderItemMapper orderItemMapper;
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("conf/spring-db.xml","conf/spring-mybatis.xml");
		userMapper = ac.getBean("userMapper",UserMapper.class);
		dictMapper = ac.getBean("dictMapper",DictMapper.class);
		addressMapper = ac.getBean("addressMapper",AddressMapper.class);
		orderMapper = ac.getBean("orderMapper",OrderMapper.class);
		orderItemMapper = ac.getBean("orderItemMapper",OrderItemMapper.class);
	}
	@After
	public void destory() {
		ac.close();
	}
	@Test
	public void testDBCPConnection() {
		BasicDataSource dataSource = ac.getBean("bds",BasicDataSource.class);
		System.out.println(dataSource);
	}
	@Test
	public void testCreateUser() {
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setUsername("gaga");
		user.setPassword("123123");
		user.setPhone("1123");
		user.setEmail("333@qq.com");
		userMapper.createUser(user);
		System.out.println(userMapper);
	}
	@Test
	public void testFindUserByUsername() {
		
		User user = userMapper.findUserByUsername("haha");
		System.out.println(user);
	}
	@Test
	public void testGerRecord() {
		int a = userMapper.getRecordCountByEmail("222@qq.com");
		int b = userMapper.getRecordCountByPhone("123123");
		System.out.println(a+":"+b);
	}
	@Test
	public void testupdatepwd() {
		userMapper.updatePassword(4, "111111");
	}
	@Test
	public void testProvince() {
		List<Province> list = dictMapper.getProvinceList();
		for (Province province : list) {
			System.out.println(province);
			
		}
	}
	
	@Test
	public void testAddOrder() {
		Order or = new Order();
		or.setGoodsCount(2);
		orderMapper.add(or);
		System.out.println("ok");
	}
	
	@Test
	public void testAddOrderItem() {
		OrderItem item = new OrderItem();
		item.setGoodsTitle("gagagaa");
		orderItemMapper.add(item);
		System.out.println("ok");
	}
}
