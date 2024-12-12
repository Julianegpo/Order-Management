package com.jegeap.service;

import com.jegeap.model.Order;

public interface OrderService {

	public Order createOrder(Order order) throws Exception;
	public Order findById(Long id) throws Exception;
	public boolean updateOrder(Order order) throws Exception;
	public boolean deleteOrder(Long id) throws Exception;
	
}
