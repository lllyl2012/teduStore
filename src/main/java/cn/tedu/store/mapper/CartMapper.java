package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;

public interface CartMapper {
	/**
	 * 添加商品到购物车
	 * @param cart
	 */
	void add(Cart cart);
	/**
	 * 获得购物车列表
	 * @param uid
	 * @return
	 */
	List<Cart> getCartList(@Param("uid") Integer uid);
	/**
	 * 更新购物车列表
	 * @param cart
	 */
	void updateCart(Cart cart);
}
