package com.jegeap.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jegeap.client.ProductClient;
import com.jegeap.model.Order;
import com.jegeap.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductClient feignProduct;
	
	@Override
	@CircuitBreaker(name = "orders", fallbackMethod = "fallbackFindById")
	public Order findById(Long id) throws Exception {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	@CircuitBreaker(name = "orders", fallbackMethod = "fallbackCreateOrder")
	public Order createOrder(Order order) throws Exception {
		return feignProduct.existProduct(order.getProduct()) 
				? repository.save(order)
				: null;
	}	

	@Override
	@Transactional
	@CircuitBreaker(name = "orders", fallbackMethod = "fallbackUpdateOrder")
	public boolean updateOrder(Order order) throws Exception {
		if (repository.existsById(order.getId())) {
            return repository.save(order) != null && order.getId() != null;
        }
		return false;
	}

	@Override
	@Transactional
	@CircuitBreaker(name = "orders", fallbackMethod = "fallbackDeleteOrder")
	public boolean deleteOrder(Long id) throws Exception {
		if(repository.findById(id).isPresent()) repository.deleteById(id);
		return repository.existsById(id);
		
	}
	
	@SuppressWarnings("unused")
	private Order fallbackFindById(Long id, Throwable ex) {
		System.out.println("fallback reach caused by -> "+ ex);
		return new Order();
	}
	
	@SuppressWarnings("unused")
	private Order fallbackCreateOrder(Order order, Throwable ex) {
		System.out.println("fallback reach caused by -> "+ ex);
		return new Order();
	}
	
	
	@SuppressWarnings("unused")
	private boolean fallbackUpdateOrder(Order order, Throwable ex) {
		System.out.println("fallback reach caused by -> "+ ex);
		return false;
	}
	
	@SuppressWarnings("unused")
	private Order fallbackDeleteOrder(Long id, Throwable ex) {
		System.out.println("fallback reach caused by -> "+ ex);
		return null;
	}

}
