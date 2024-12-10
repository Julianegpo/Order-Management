package com.jegeap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jegeap.model.Order;
import com.jegeap.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService service;
	
	@PostMapping("/create")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		if (order == null || order.getId() == null) {
	        return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
	    } else {
	        return service.createOrder(order) != null 
	        		? new ResponseEntity<Order>(HttpStatus.CREATED) 
	        		: new ResponseEntity<Order>(HttpStatus.CONFLICT);
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOneByOrderId(@PathVariable Long id) {
		Order order = service.findById(id);
		return (order != null && order.getId() != null) 
				? new ResponseEntity<Order>(order, HttpStatus.OK) 
				: new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Long id) {
		Order order = service.findById(id);
		if(order == null) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		} else {
			return service.updateOrder(order) 
					? new ResponseEntity<Order>(HttpStatus.OK)
					: new ResponseEntity<Order>(HttpStatus.CONFLICT);					
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {		
		return service.deleteOrder(id) == null 
				? new ResponseEntity<Order>(HttpStatus.OK) 
				: new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
	}
	
}
