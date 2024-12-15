package com.jegeap.service;

import java.util.List;

import com.jegeap.dto.ProductDTO;

public interface ProductService {	
	public List<ProductDTO> getAll() throws Exception;
	public ProductDTO searchProduct(Long id) throws Exception;
	public boolean existProduct(Long id) throws Exception;
}
