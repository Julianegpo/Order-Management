package com.jegeap.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<List<Product>> getAll()  {
		List<Product> products;
		try {
			products = service.getAll().stream()
			.map(prod -> {
				prod.setPort(request.getLocalPort());
				return prod;
			}).collect(Collectors.toList());
			
			return new ResponseEntity<List<Product>>(products, 
					!products.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOneByProductId(@PathVariable Long id) {
		Product product;
		try {
			product = service.searchProduct(id);
			product.setPort(request.getLocalPort());
			
			return ResponseEntity.ok(service.searchProduct(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/exist/{id}")
	public ResponseEntity<Boolean> existProduct(@PathVariable Long id) {
		boolean existsProduct;
		try {
			existsProduct = service.existProduct(id);
			
			return new ResponseEntity<Boolean>(existsProduct, 
					existsProduct ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
	}
	
}
