package com.jegeap.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jegeap.model.Product;
import com.jegeap.repository.ProductRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;	
	
	@Override
	@CircuitBreaker(name = "products", fallbackMethod = "fallbackGetAll")
	public List<Product> getAll() throws Exception {
		return repository.findAll();
	}
	
	@Override
	@CircuitBreaker(name = "products", fallbackMethod = "fallbackSearchProduct")
	public Product searchProduct(Long id) throws Exception {
		return repository.findById(id).orElse(new Product());
	}

	@Override
	@CircuitBreaker(name = "products", fallbackMethod = "fallbackExistProduct")
	public boolean existProduct(Long id) throws Exception {
		return repository.existsById(id);
	}
	
	@SuppressWarnings("unused")
	private List<Product> fallbackGetAll(Throwable ex) {
		System.out.println("fallback reach caused by -> "+ ex);
		return Collections.emptyList();
	}
	
	@SuppressWarnings("unused")
	private Product fallbackSearchProduct(Long id, Throwable ex) {
		System.out.println("fallback reach caused by -> "+ ex);
		return new Product();
	}
	
	@SuppressWarnings("unused")
	private boolean fallbackExistProduct(Long id, Throwable ex) {
		System.out.println("fallback reach caused by -> "+ ex);
		return false;
	}
	
}
