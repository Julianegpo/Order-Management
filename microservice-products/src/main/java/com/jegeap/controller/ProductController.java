package com.jegeap.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jegeap.model.Product;
import com.jegeap.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ProductService service;
	
	@GetMapping("/list")
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(
				service.getAll()
				.stream()
				.map(prod -> {
					//prod.setPort(port);
					prod.setPort(request.getLocalPort());
					return prod;
				}).collect(Collectors.toList())
				);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOneByProductId(@PathVariable Long id) {
		Product product = service.searchProduct(id);
		product.setPort(request.getLocalPort());
		return ResponseEntity.ok(service.searchProduct(id));
	}
	
	
}
