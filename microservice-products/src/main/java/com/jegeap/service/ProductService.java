package com.jegeap.service;

import java.util.List;

import com.jegeap.model.Product;

public interface ProductService {
	
	public List<Product> getAll() throws Exception;
	public Product searchProduct(Long id) throws Exception;
	public boolean existProduct(Long id) throws Exception;

}
