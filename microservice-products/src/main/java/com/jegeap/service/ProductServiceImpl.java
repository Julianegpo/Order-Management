package com.jegeap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jegeap.model.Product;
import com.jegeap.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;	
	
	@Override
	public List<Product> getAll() {
		return repository.findAll();
	}
	
	@Override
	public Product searchProduct(Long id) {
		return repository.findById(id).orElse(new Product());
	}

}
