package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.AddressService;
import cn.tedu.store.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
	@Resource
	private OrderService orderService;
	@Resource
	private AddressService addressService;
	
	/**
	 * 前往生成订单页面
	 */
	@RequestMapping("/toAdd.do")
	public String toAdd(HttpSession session,ModelMap map,List<OrderItem> orderItems) {
		System.out.println(orderItems);
		Integer uid = this.getUidFromSession(session);
		Address defaultAddress = addressService.findDefaultByUid(uid);
		map.put("address", defaultAddress);
		return "orderConfirm";
	}
	
	/**
	 * 生成订单
	 * @param session
	 * @param orderItems
	 * @return
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public ResponseResult<Void> addOrder(HttpSession session,List<OrderItem> orderItems) {
		ResponseResult<Void> rr = new ResponseResult<Void>();
		Integer uid = this.getUidFromSession(session);
		String username = this.getUsernameFromSession(session);
		String phone = this.getPhoneFromSession(session);
		//获得用户默认地址
		Address defaultAddress = addressService.findDefaultByUid(uid);
		//生成订单
		Order order = new Order();
		order.setUserId(uid);
		order.setRecvPerson(username);
		order.setRecvPhone(phone);
		order.setRecvDistrict(defaultAddress.getRecvDistrict());
		order.setRecvAddr(defaultAddress.getRecvAddr());
		order.setRecvAddrCode(defaultAddress.getRecvDddrCode());
		order.setPrice(0);
		order.setStatus(1);
		order.setGoodsCount(0);
		
		//生成订单条目
		try {
			orderService.createOrder(order, orderItems);
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("生成订单成功");
		} catch (Exception e) {
			e.printStackTrace();
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("未知错误，生成订单失败");
		}
		return rr;
	}
}
