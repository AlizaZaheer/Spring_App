package com.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.pojo.OrderDetails;

public interface OrderRepo extends JpaRepository<OrderDetails, Long> {


	OrderDetails findByPaymentId(String paymentId);

}
