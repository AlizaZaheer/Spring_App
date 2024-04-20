package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.pojo.OrderDetails;
import com.restaurant.repo.OrderRepo;
import java.util.Random;


@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;
	
	public OrderDetails addOrder(OrderDetails order) {
		return orderRepo.save(order);
	}
	
	public OrderDetails getOrderByOrderId(Long orderId) {
		return orderRepo.findById(orderId).orElse(null);
	}
	
	public List<OrderDetails> getAllOrders(){
		return orderRepo.findAll();
	}

	public String randomPaymentIdGenerator() {
		Random random=new Random();
		long paymentId=100_000_000_000L+(random.nextLong()%100_000_000_000L);
		return "C_O_D"+String.format("%012d", paymentId);
	}

	public OrderDetails getOrderByPaymentId(String paymentId) {
		return orderRepo.findByPaymentId(paymentId);	}
}
