package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;

public interface CartService {
	void add(Cart cart);
	/**
	 * 获得用户的购物车订单
	 * @param uid
	 * @return
	 */
	List<Cart> getCartList(Integer uid);
	/**
	 * 修改购物车订单
	 * @param cart
	 */
	void updateCart(Cart cart);
}
