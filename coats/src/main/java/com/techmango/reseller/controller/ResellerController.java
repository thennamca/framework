package com.techmango.reseller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techmango.reseller.entity.Order;
import com.techmango.reseller.services.ResellerService;

@Controller
@RequestMapping("tables")
public class ResellerController {

	@Autowired
	private ResellerService service;

	@GetMapping("coats_reseller_bulk_order")
	public ResponseEntity<List<Order>> getOrders() {

		List<Order> orders = service.getOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	@GetMapping("coats_reseller_bulk_order/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Integer id) {
		Order order = service.getOrder(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@PostMapping("coats_reseller_bulk_order")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order orders = service.createOrder(order);
		return new ResponseEntity<Order>(orders, HttpStatus.OK);

	}

	@PutMapping("coats_reseller_bulk_order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") int id, @RequestBody Order order) {

		Order o = service.updateOrder(id, order);
		return new ResponseEntity<Order>(o, HttpStatus.OK);
	}

	@DeleteMapping("coats_reseller_bulk_order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") int id) {
		boolean isDeleted = service.deleteOrder(id);
		if (isDeleted) {
			String responseContent = "Order has been deleted successfully";
			return new ResponseEntity<String>(responseContent, HttpStatus.OK);
		}
		String error = "Error while deleting order from database";
		return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
