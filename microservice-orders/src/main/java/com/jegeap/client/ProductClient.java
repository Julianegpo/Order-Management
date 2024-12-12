package com.jegeap.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jegeap.model.Product;

@FeignClient(name="service-products")
public interface ProductClient {

	@GetMapping("/list")
	public Product getAll();
	@GetMapping("/{id}")
	public Product getOneByProductId(@PathVariable Long id);
	@GetMapping("/exist/{id}")
	public boolean existProduct(@PathVariable Long id);
	
}
