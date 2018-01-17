package cn.tedu.store.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	/**
	 * 生成验证码图片验证器
	 * value映射url
	 * consumes=“iamge/png”设置响应头content-type
	 */
	@RequestMapping(value="code.do",produces="image/png")
	@ResponseBody
	public byte[] code(HttpSession session) throws IOException{
		String code = genCode(4);
		session.setAttribute("code", code);
		byte[] png = createPng(code);
		return png;
	}
	private byte[] createPng(String code) throws IOException{
		BufferedImage img = new BufferedImage(100, 40, BufferedImage.TYPE_3BYTE_BGR);
		img.setRGB(50, 20, 0x00ffff);
		Graphics2D graphics = img.createGraphics();
		//生成随即颜色
		Random random = new Random();
		Color c = new Color(random.nextInt(0xffffff));
		//填充图像背景
		graphics.setColor(c);
		graphics.fillRect(0, 0, 100, 40);
		//绘制500个随机点
		for(int i=0;i<500;i++) {
			int x = random.nextInt(100);
			int y = random.nextInt(40);
			int rgb = random.nextInt(0xffffff);
			img.setRGB(x, y, rgb);
		}
		//设置平滑抗锯齿
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
		graphics.setColor(new Color(random.nextInt(0xffffff)));
		graphics.drawString(code, 10, 30);
		
		//画几条线
		for(int i=0;i<10;i++) {
			int x1 = random.nextInt(100);
			int y1 = random.nextInt(40);
			int x2 = random.nextInt(100);
			int y2 = random.nextInt(40);
			graphics.drawLine(x1, y1, x2, y2);
		}
		//利用imageIo将Img编码为png
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);
		out.close();
		byte[] bytes = out.toByteArray();
		return bytes;
	}
	private static String chs = "ABCDEFGHIJKLMNOPQRSTUVWXVZabcdefghijklmnopqrstuvwxyz0123456789";
	private String genCode(int len) {
		char[] code = new char[len];
		Random random = new Random();
		for(int i=0;i<code.length;i++) {
			code[i]=chs.charAt(random.nextInt(chs.length())); 
		}
		return new String(code);
		
	}
	
	/**
	 * 检查验证码
	 */
	@RequestMapping("/checkCode.do")
	@ResponseBody
	public ResponseResult<Void> checkCode(@RequestParam("code")String code,HttpSession session){
		String c = (String)session.getAttribute("code");
		ResponseResult<Void> rr = new ResponseResult<Void>();
		if(c !=null && c.equalsIgnoreCase(code)) {
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("验证码正确");
			return rr;
		}
		rr.setState(ResponseResult.STATE_ERROR);
		rr.setMessage("验证码错误");
		return rr;
	}
	
	/**
	 * 下载图片
	 */
	@RequestMapping(value="/downloadImage.do",produces="image/png")
	@ResponseBody
	public byte[] downloadImage(HttpServletResponse response) throws Exception{
		response.setHeader("Content-Disposition","attachment;filename=\"ok.png\"");
		byte[] bytes = this.createPng("OK");
		return bytes;
	}
	
	/**
	 * 下载Excel
	 */
	@RequestMapping(value="downloadExcel.do",produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@ResponseBody
	public byte[] downloadExcel(HttpServletResponse response) throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\"ok.xlsx\"");
		byte[] bytes = this.createExcel();
		return bytes;
	}
	/**
	 * 利用Excel API POI 创建Excel对象
	 */
	private byte[] createExcel() throws IOException{
		//创建工作簿子
		XSSFWorkbook workbook = new XSSFWorkbook();
		//在工作簿中添加工作表
		XSSFSheet sheet1 = workbook.createSheet("花名册");
		//在工作表中添加两行
		XSSFRow head = sheet1.createRow(0);
		XSSFRow row = sheet1.createRow(1);
		//第一行做为表头
		XSSFCell c0 = head.createCell(0);
		//表头第一个格子添加“编号”
		c0.setCellValue("编号");
		head.createCell(1).setCellValue("姓名");
		head.createCell(2).setCellValue("年龄");
		
		row.createCell(0).setCellValue(1);
		row.createCell(1).setCellValue("范传奇");
		row.createCell(2).setCellValue(12);
		
		//将Excel对象保存为bytes
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		out.close();
		byte[] bytes = out.toByteArray();
		return bytes;
	}
	/**
	 * 显示上载表单
	 */
	@RequestMapping("uploadForm.do")
	public String uploadForm() {
		return "upload";
	}
	
	/**
	 * 处理上载表单内容
	 */
	@RequestMapping(value="upload.do", 
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> upload(
		@RequestParam("userfile1") MultipartFile image,
		@RequestParam("username") String username,
		HttpServletRequest request) 
		throws IOException {
		//打桩输出上载结果
		System.out.println(username);
		System.out.println(image);
		//获取上载文件信息
		System.out.println(image.getContentType());
		System.out.println(image.getName());
		System.out.println(image.getOriginalFilename());
		System.out.println(image.getSize());
		//保存到文件系统
		String path="/images/upload";//WEB路径
		path = request.getServletContext()
				.getRealPath(path);
		System.out.println(path); 
		//创建upload文件夹
		File dir = new File(path);
		dir.mkdir();
		File file=new File(dir, 
				image.getOriginalFilename());
		//将上载文件保存到文件中
		image.transferTo(file);
		
		ResponseResult<Void> rr=new ResponseResult<Void>();
		rr.setState(ResponseResult.STATE_OK);
		rr.setMessage("上载成功");
		return rr;
	}
	
	/**
	 * 处理上载请求，保存多个文件
	 */
	@RequestMapping(value="/uploadImages.do", 
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> uploadImages(
		@RequestParam("images") MultipartFile[] images,
		@RequestParam("username") String username,
		HttpServletRequest request) 
		throws IOException {
		//保存到文件系统
		String path="/images/upload";//WEB路径
		path = request.getServletContext()
				.getRealPath(path);
		System.out.println(path); 
		//创建upload文件夹
		File dir = new File(path);
		dir.mkdir();
		for(MultipartFile image:images) {
			File file=new File(dir, 
			image.getOriginalFilename());
			System.out.println("save:"+file);
			//将上载文件保存到文件中
			image.transferTo(file);
		}
		ResponseResult<Void> rr=new ResponseResult<Void>();
		rr.setState(ResponseResult.STATE_OK);
		rr.setMessage("上载成功");
		return rr;
	}
}

