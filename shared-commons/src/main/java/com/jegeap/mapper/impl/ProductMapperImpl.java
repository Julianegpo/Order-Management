package com.jegeap.mapper.impl;

import com.jegeap.dto.ProductDTO;
import com.jegeap.mapper.interfaces.ProductMapper;
import com.jegeap.model.Product;

public class ProductMapperImpl implements ProductMapper {

	@Override
	public ProductDTO toDTO(Product product) {
		return new ProductDTO(product.getId(), product.getDescription(), product.getPrice());
	}

	@Override
	public Product toModel(ProductDTO productDTO) {
		return new Product(productDTO.getId(), productDTO.getDescription(), productDTO.getPrice());
	}

}
