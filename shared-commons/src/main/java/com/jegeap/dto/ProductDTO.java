package com.jegeap.dto;

import lombok.Data;

@Data
public class ProductDTO {
	
	private Long id;
	private String description;
	private Double price;
	
	public ProductDTO() {
		super();
	}

	public ProductDTO(Long id, String description, Double price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
