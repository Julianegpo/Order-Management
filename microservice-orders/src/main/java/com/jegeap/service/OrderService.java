package com.jegeap.service;

import com.jegeap.model.Order;

public interface OrderService {

	public Order createOrder(Order order);
	public Order findById(Long id);
	public boolean updateOrder(Order order);
	public Order deleteOrder(Long id);
	
}
