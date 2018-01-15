package cn.tedu.store.service;

import javax.servlet.http.HttpSession;

import cn.tedu.store.entity.User;
/**
 * 此接口用于用户的各种操作
 * @author soft01
 *
 */
public interface UserService {
	/**
	 * 注册用户
	 * @param user
	 */
	void register(User user);
	void register(String username,String password,String phone,String email);
	/**
	 * 根据名字查找用户
	 */
	User findUserByUsername(String username);
	/**
	 * 检查电话号码是否存在
	 * @param phone
	 * @return
	 */
	boolean checkPhoneExists(String phone);
	/**
	 * 检查邮箱是否已经存在
	 * @param email
	 * @return
	 */
	boolean checkEmailExists(String email);
	/**
	 * 登录
	 */
	User login(String username,String password);
	/**
	 * 更改用户信息
	 */
	void updateUserInfo(HttpSession session,Integer id,String username,String phone,String email);
	/**
	 * 修改用户密码
	 */
	Integer updatePassword(String oldPassword,Integer id,String newPassword);
}
