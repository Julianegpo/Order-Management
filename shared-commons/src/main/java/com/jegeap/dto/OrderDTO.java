package com.jegeap.dto;

import com.jegeap.util.OrderStatusEnum;


public class OrderDTO {
	
	private Long id;
	private Long product;
	private int quantity;
	private OrderStatusEnum status;
	
	public OrderDTO() {
		super();
	}

	public OrderDTO(Long id, Long product, int quantity, OrderStatusEnum status) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderStatusEnum getStatus() {
		return status;
	}
	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}
	
	
}
