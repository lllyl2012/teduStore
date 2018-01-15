package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

public interface OrderService {
	public void add(Order order);
	public void add(OrderItem orderItem);
	public void createOrder(Order order,List<OrderItem> orderItems);
}
