package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.AddressService;
import cn.tedu.store.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource
	private UserService userService;
	@Resource
	private AddressService addressService;
	/**
	 * 检查用户名是否被使用
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public ResponseResult<Void> checkUsername(String username) {
		User user = userService.findUserByUsername(username);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		if (user == null) {
			rr.setData(null);
			rr.setMessage("该昵称未被使用");
			rr.setState(ResponseResult.STATE_OK);
		} else {
			rr.setData(null);
			rr.setMessage("该昵称已被使用");
			rr.setState(ResponseResult.STATE_ERROR);
		}
		return rr;
	}

	/**
	 * 到注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/register.do")
	public String showRegister() {
		return "register";
	}

	/**
	 * 检查手机号码是否被使用
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping("/checkPhone.do")
	@ResponseBody
	public ResponseResult<Void> checkPhone(String phone) {
		boolean flag = userService.checkPhoneExists(phone);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		if (flag) {
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("号码已被注册");
		} else {
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("该号码可用");
		}
		return rr;
	}

	/**
	 * 检查邮箱是否被使用
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("/checkEmail.do")
	@ResponseBody
	public ResponseResult<Void> checkEmail(String email) {
		boolean flag = userService.checkEmailExists(email);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		if (flag) {
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("邮箱已被注册");
		} else {
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("该邮箱可用");
		}
		return rr;
	}

	/**
	 * 处理注册请求
	 * 
	 * @param username
	 * @param password
	 * @param phone
	 * @param email
	 * @return
	 */
	@RequestMapping("/handleRegister.do")
	@ResponseBody
	public ResponseResult<Void> handleRegister(@RequestParam("uname") String username,
			@RequestParam("upwd") String password, String phone, String email) {
		userService.register(username, password, phone, email);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		rr.setState(ResponseResult.STATE_OK);
		rr.setMessage("注册成功");
		return rr;
	}

	/**
	 * 登录页面
	 */
	@RequestMapping("/login.do")
	public String login() {
		return "login";
	}

	/**
	 * 处理登录
	 */
	@RequestMapping("/handleLogin.do")
	@ResponseBody
	public ResponseResult<Void> handleLogin(@RequestParam("lname") String username,
			@RequestParam("lwd") String password, HttpSession session) {
		System.out.println(username + ":" + password);
		User user = userService.login(username, password);

		ResponseResult<Void> rr = new ResponseResult<Void>();
		if (user != null) {
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("登录成功");
			session.setAttribute("uid", user.getId());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("phone", user.getPhone());
		} else {
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("用户名或密码不正确");
		}

		return rr;
	}

	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/checkLoginUsername.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> checkLoginUsername(@RequestParam("username") String username) {
		User user = userService.findUserByUsername(username);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		if (user != null) {
			rr.setData(null);
			rr.setMessage("");
			rr.setState(ResponseResult.STATE_OK);
		} else {
			rr.setData(null);
			rr.setMessage("不存在该用户");
			rr.setState(ResponseResult.STATE_ERROR);
		}
		return rr;
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		// session.invalidate();
		session.removeAttribute("uid");
		session.removeAttribute("username");
		return "redirect:../main/index.do";
	}

	/**
	 * 用户信息页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/user_center.do")
	public String showUserCenter(HttpSession session, ModelMap map) {
		User user = userService.findUserByUsername((String) session.getAttribute("username"));
		map.put("user", user);
		return "user_center";
	}

	/**
	 * 修改用户信息页面
	 * @param session
	 * @param username
	 * @param email
	 * @param phone
	 * @return
	 */
	@RequestMapping("/update_user_info.do")
	@ResponseBody
	public ResponseResult<Void> updateUserInfo(HttpSession session, String username, String email, String phone) {
		int id = this.getUidFromSession(session);
		userService.updateUserInfo(session, id, username, phone, email);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		rr.setState(1);
		rr.setMessage("修改成功");
		return rr;
	}

	/**
	 * 修改密码
	 * 
	 * @param session
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "update_password.do")
	@ResponseBody
	public ResponseResult<Void> hendleUpdatePassword(HttpSession session, String oldPassword, String newPassword) {
		Integer id = this.getUidFromSession(session);
		Integer states = userService.updatePassword(oldPassword, id, newPassword);
		String message = "";
		ResponseResult<Void> rr = new ResponseResult<Void>();
		rr.setState(states);
		message = states == 1 ? "修改密码成功" : "旧密码不正确";
		rr.setMessage(message);
		return rr;
	}

	@RequestMapping("/user_password.do")
	public String showPasswordUI() {
		return "user_password";
	}

	@RequestMapping("/address.do")
	public String showAddress(HttpSession session,ModelMap map) {
		Integer uid = this.getUidFromSession(session);
		List<Address> list = addressService.findAddressByUid(uid);
		map.put("allAddress", list);
		return "address";
	}
}
