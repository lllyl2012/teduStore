package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.mapper.OrderItemMapper;
import cn.tedu.store.mapper.OrderMapper;
@Service("orderSerivce")
public class OrderServiceImpl implements OrderService{
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderItemMapper orderItemMapper;
	
	/**
	 * 创建订单
	 * mybatis事务不能直接调用持久层，只能调用自己的方法去调用持久层
	 */
	@Transactional
	public void createOrder(Order order,List<OrderItem> orderItems) {
		this.add(order);
		Integer orderId = order.getId();
		for(OrderItem orderItem:orderItems) {
			orderItem.setOrderId(orderId);
			this.add(orderItem);
		}
	}
	/**
	 * 创建订单
	 */
	public void add(Order order) {
		orderMapper.add(order);
	}
	/**
	 * 常见订单条目
	 */
	public void add(OrderItem orderItem) {
		orderItemMapper.add(orderItem);
	}
	
}
