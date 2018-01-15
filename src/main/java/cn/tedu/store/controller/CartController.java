package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.CartService;
import cn.tedu.store.service.GoodsService;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController{
	@Resource
	private CartService cartService;
	@Resource
	private GoodsService goodsService;
	
	/**
	 * 添加商品到购物
	 * @param cart
	 * @param session
	 * @return
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public ResponseResult<Void> handleAddCart(Cart cart,HttpSession session) {
		ResponseResult<Void> rr = new ResponseResult<Void>();
		Integer uid = this.getUidFromSession(session);
		cart.setUserId(uid);
		if(cart.getGoodsTitle()==null) {
			Goods goods = goodsService.getGoodsById(cart.getGoodsId());
			cart.setGoodsImage(goods.getImage());
			long price =  goods.getPrice();
			int pri = (int)price;
			cart.setGoodsPrice(pri);
			cart.setNum(1);
			cart.setGoodsTitle(goods.getTitle());
		}
		try {
			cartService.add(cart);
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("添加失败");
		}
		return rr;
	}
	
	/**
	 * 到购物车页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/list.do")
	public String getCartList(HttpSession session,ModelMap map) {
		Integer uid = this.getUidFromSession(session);
		List<Cart> list = cartService.getCartList(uid);
		map.put("cartList", list);
		return "cart";
	}
	
	/**
	 * 减少或增加商品
	 */
	@RequestMapping("/reduc.do")
	@ResponseBody
	public ResponseResult<Void> reducCart(Cart cart,HttpSession session){
		ResponseResult<Void> rr = new ResponseResult<Void>();
		Integer uid = this.getUidFromSession(session);
		cart.setUserId(uid);
		try {
			cartService.updateCart(cart);
			rr.setState(ResponseResult.STATE_OK);
			rr.setMessage("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			rr.setState(ResponseResult.STATE_ERROR);
			rr.setMessage("修改失败");
		}
		return rr;
	}
}
