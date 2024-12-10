package com.jegeap.service;

import java.util.List;

import com.jegeap.model.Product;

public interface ProductService {
	
	public List<Product> getAll();

	public Product searchProduct(Long id);

}
