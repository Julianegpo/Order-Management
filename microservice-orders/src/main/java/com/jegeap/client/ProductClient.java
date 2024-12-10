package com.jegeap.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jegeap.model.Product;

@FeignClient(name="service-products")
public class ProductClient {

	// Mapeamos a donde vamos a dirigir la peticion	(direccion del mapping objetivo)
	@GetMapping("/buscar/{id}")
	public Product buscar(@PathVariable(name = "id") Long id) {
		return null;
	}
	
}
