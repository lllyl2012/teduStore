package cn.tedu.store.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;
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
		user.setUsername(username);
		user.setPassword(password);
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
		if(user.getPassword().equals(password)) {
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
		if(user.getPassword().equals(oldPassword)) {
			userMapper.updatePassword(id, newPassword);
			state = 1;
		}
		return state;
	}
	
}
