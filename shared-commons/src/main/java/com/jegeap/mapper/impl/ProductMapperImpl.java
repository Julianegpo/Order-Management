package com.jegeap.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<ProductDTO> toDTOList(List<Product> products) {
		return products.stream()
                .map(product -> this.toDTO(product))
                .collect(Collectors.toList());
	}

}
