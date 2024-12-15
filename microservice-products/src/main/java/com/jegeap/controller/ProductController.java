package com.jegeap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jegeap.dto.ProductDTO;
import com.jegeap.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/list")
	public ResponseEntity<List<ProductDTO>> getAll()  {
		try {
			List<ProductDTO> products = service.getAll();
			return new ResponseEntity<List<ProductDTO>>(products, 
					!products.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getOneByProductId(@PathVariable Long id) {
		try {	
			return ResponseEntity.ok(service.searchProduct(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ProductDTO>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/exist/{id}")
	public ResponseEntity<Boolean> existProduct(@PathVariable Long id) {
		try {
			boolean existsProduct = service.existProduct(id);			
			return new ResponseEntity<Boolean>(existsProduct, 
					existsProduct ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
	}
	
}
