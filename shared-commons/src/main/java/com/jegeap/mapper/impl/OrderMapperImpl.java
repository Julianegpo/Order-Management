package com.jegeap.mapper.impl;

import com.jegeap.dto.OrderDTO;
import com.jegeap.mapper.interfaces.OrderMapper;
import com.jegeap.model.Order;

public class OrderMapperImpl implements OrderMapper {

	@Override
	public OrderDTO toDTO(Order order) {
		return new OrderDTO(order.getId(), order.getProduct(), order.getQuantity(), order.getStatus());
	}

	@Override
	public Order toModel(OrderDTO orderDTO) {
		return new Order(orderDTO.getId(), orderDTO.getProduct(), orderDTO.getQuantity(), orderDTO.getStatus());
	}

}
