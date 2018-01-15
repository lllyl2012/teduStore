package cn.tedu.store.mapper;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

public interface UserMapper {
	/**
	 * 插入用户
	 * @param user
	 */
	void createUser(User user);
	/**
	 * 根据用户名查找用户
	 */
	User findUserByUsername(@Param("username") String username);
	/**
	 * 查询该邮箱是否被注册
	 * @param email
	 * @return
	 */
	Integer getRecordCountByEmail(@Param("email") String email);
	/**
	 * 查询该电话号码是否被注册
	 * @param phone
	 * @return
	 */
	Integer getRecordCountByPhone(@Param("phone") String phone);
	/**
	 * 修改用户个人数据接口
	 */
	void updateUserInfo(@Param("id") int id,@Param("username") String username,@Param("phone") String phone,@Param("email") String email);
	/**
	 * 修改密码
	 */
	void updatePassword(@Param("id") Integer uid,@Param("password") String newPassword);
	/**
	 * 根据id找用户
	 */
	User findUserById(@Param("id") int id);
}
