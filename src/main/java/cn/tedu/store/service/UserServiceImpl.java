package cn.tedu.store.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;
	//从spring中找到一个BeanID为config的bean，获取器salt属性的值，注入到salt变量中
	@Value("#{config.salt}")
	private String salt;
	
	public void register(User user) {
		User user1 = this.findUserByUsername(user.getUsername());
		if(user1==null) {
			userMapper.createUser(user);
			System.out.println("注册成功");
		}else {
			System.out.println("该用户名已经存在");
		}
	}
	public void register(String username, String password, String phone, String email) {
		User user = new User();
		Date now = new Date();
		System.out.println("userServiceImpl.reigster():"+salt);
		user.setUsername(username);
		//密码摘要加密
		
		String pwd = DigestUtils.md5Hex(password+salt);
		user.setPassword(pwd);
		user.setPhone(phone);
		user.setEmail(email);
		user.setDisabled(0);
		user.setCreatedTime(now);
		user.setCreatedUser("[System]");
		user.setModifiedTime(now);
		user.setModifiedUser("[System]");
		this.register(user);
	}
	
	public User findUserByUsername(String username) {
		User user = userMapper.findUserByUsername(username);
		return user;
	}
	public boolean checkPhoneExists(String phone) {
		return userMapper.getRecordCountByPhone(phone)>0;
	}
	public boolean checkEmailExists(String email) {
		return userMapper.getRecordCountByEmail(email)>0;
	}
	public User login(String username, String password) {
		System.out.println("login");
		User user = userMapper.findUserByUsername(username);
		if(user==null) {
			return null;
		}
		//比较摘要加密后的密码
		String pwd = DigestUtils.md5Hex(password+salt);
		if(user.getPassword().equals(pwd)) {
			return user;
		}else {
			return null;
		}
	}
	public void updateUserInfo(HttpSession session,Integer id, String username, String phone, String email) {
		if(username==null ||"".equals(username)) {
			username = (String)session.getAttribute("username");
		}
		if(email==null || "".equals(email)) {
			email = (String)session.getAttribute("email");
		}
		if(phone == null || "".equals(phone)) {
			phone = (String)session.getAttribute("phone");
		}
		userMapper.updateUserInfo(id, username, phone, email);
	}
	public Integer updatePassword(String oldPassword,Integer id, String newPassword) {
		int state = -1;
		User user = userMapper.findUserById(id);
		String pwd = DigestUtils.md5Hex(user.getPassword()+salt);
		if(user.getPassword().equals(oldPassword)) {
			userMapper.updatePassword(id, newPassword);
			state = 1;
		}
		return state;
	}
	
}
