package com.jegeap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jegeap.dto.ProductDTO;
import com.jegeap.model.Product;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	ProductDTO toDTO(Product product);
    Product toModel(ProductDTO userDTO);
	
}
