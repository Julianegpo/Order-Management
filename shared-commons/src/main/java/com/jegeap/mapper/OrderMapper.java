package com.jegeap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jegeap.dto.OrderDTO;
import com.jegeap.model.Order;

@Mapper
public interface OrderMapper {

	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	OrderDTO toDTO(Order user);
    Order toModel(OrderDTO userDTO);
	
}
