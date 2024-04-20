package com.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.pojo.OrderDetails;
import com.restaurant.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping("/createOrder")
	public ResponseEntity<?> createOrder(@RequestBody OrderDetails orderDetails) {
		return ResponseEntity.ok(orderService.addOrder(orderDetails));
	}
	
	@GetMapping("/orderId/{orderId}")
	public OrderDetails getOrderById(@PathVariable Long orderId) {
		return orderService.getOrderByOrderId(orderId);
	}
	
	@GetMapping("/idForCod")
	public String paymentIdForCod() {
		String paymentId=orderService.randomPaymentIdGenerator();
		
		while(isPaymentIdExists(paymentId)) {
			paymentId=orderService.randomPaymentIdGenerator();
		}
		return paymentId;
	}
	
	public boolean isPaymentIdExists(String randomPaymentId) {
		OrderDetails order=orderService.getOrderByPaymentId(randomPaymentId);
		return(order!=null);
	}
	@GetMapping("/paymentId/{paymentId}")
	public OrderDetails getOrderByPaymentId(@PathVariable String paymentId) {
		return orderService.getOrderByPaymentId(paymentId);
	}
	
	@GetMapping("")
	public List<OrderDetails> getAllOrders(){
		return orderService.getAllOrders();
	}
}
