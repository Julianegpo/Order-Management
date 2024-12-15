package com.jegeap.mapper.interfaces;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jegeap.dto.ProductDTO;
import com.jegeap.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	ProductDTO toDTO(Product product);
	List<ProductDTO> toDTOList(List<Product> products);
    Product toModel(ProductDTO userDTO);
	
}
