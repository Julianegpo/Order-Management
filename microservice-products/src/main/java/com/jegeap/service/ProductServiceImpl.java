package com.jegeap.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jegeap.dto.ProductDTO;
import com.jegeap.mapper.interfaces.ProductMapper;
import com.jegeap.model.Product;
import com.jegeap.repository.ProductRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@SuppressWarnings("unused")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	@CircuitBreaker(name = "products", fallbackMethod = "fallbackGetAll")
	public List<ProductDTO> getAll() throws Exception {		
		return productMapper.toDTOList(repository.findAll());
	}
	
	@Override
	@CircuitBreaker(name = "products", fallbackMethod = "fallbackSearchProduct")
	public ProductDTO searchProduct(Long id) throws Exception {
		return productMapper.toDTO(repository.findById(id).orElse(null));
	}

	@Override
	@CircuitBreaker(name = "products", fallbackMethod = "fallbackExistProduct")
	public boolean existProduct(Long id) throws Exception {
		return repository.existsById(id);
	}
	
	private List<ProductDTO> fallbackGetAll(Throwable ex) {
		System.out.println("fallbackGetAll.fallback reach caused by -> "+ ex);
		return Collections.emptyList();
	}
	
	private ProductDTO fallbackSearchProduct(Long id, Throwable ex) {
		System.out.println("fallbackSearchProduct.fallback reach caused by -> "+ ex);
		return new ProductDTO();
	}	
	
	private boolean fallbackExistProduct(Long id, Throwable ex) {
		System.out.println("fallbackExistProduct.fallback reach caused by -> "+ ex);
		return false;
	}
	
}
