package com.jegeap.dto;

import com.jegeap.util.OrderStatusEnum;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class OrderDTO {
	private Long id;
	private Long product;
	private int quantity;
	private OrderStatusEnum status;
}
