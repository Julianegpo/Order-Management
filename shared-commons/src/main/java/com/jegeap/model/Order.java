package com.jegeap.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jegeap.util.OrderStatusEnum;

@Entity
@Table(name="ORDERS")
public class Order implements Serializable {
	
	private static final long serialVersionUID = -585664194565962303L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="PRODUCT")
	private Long product;
	@Column(name="QUANTITY")
	private int quantity;
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatusEnum status = OrderStatusEnum.CREATED;
	
	public Order() {
		super();
	}

	public Order(Long id, Long product, int quantity, OrderStatusEnum status) {
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id, product, quantity, status);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id) && Objects.equals(product, other.product) && quantity == other.quantity
				&& status == other.status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", product=" + product + ", quantity=" + quantity + ", status=" + status + "]";
	}
	
}
