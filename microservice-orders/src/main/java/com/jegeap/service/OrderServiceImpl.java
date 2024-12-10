package com.jegeap.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.jegeap.client.ProductClient;
import com.jegeap.model.Order;
import com.jegeap.repository.OrderRepository;

@Service
@Primary
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductClient feignClient;
	
	@Override
	@Transactional
	public Order createOrder(Order order) {
		return repository.save(order);
	}
	
	@Override
	public Order findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public boolean updateOrder(Order order) {
		if (repository.existsById(order.getId())) {
            return repository.save(order) != null && order.getId() != null;
        }
		return false;
	}

	@Override
	@Transactional
	public Order deleteOrder(Long id) {
		if(repository.findById(id).isPresent()) repository.deleteById(id);
		return repository.findById(id).orElse(null);
		
	}

}
