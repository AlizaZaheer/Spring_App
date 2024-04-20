package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.pojo.OrderDetails;
import com.restaurant.repo.OrderRepo;
import com.restaurant.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepo orderRepo;

    @Test
    public void testAddOrder() {
        OrderDetails order = new OrderDetails();
        order.setName("John");
        order.setEmail("john@example.com");
        order.setAmount(100.0);

        when(orderRepo.save(order)).thenReturn(order);

        OrderDetails addedOrder = orderService.addOrder(order);

        assertEquals(order, addedOrder);
        verify(orderRepo).save(order);
    }

    @Test
    public void testGetOrderByOrderId() {
        OrderDetails order = new OrderDetails();
        order.setOrderId(1L);

        when(orderRepo.findById(anyLong())).thenReturn(Optional.of(order));

        OrderDetails retrievedOrder = orderService.getOrderByOrderId(1L);

        assertEquals(order, retrievedOrder);
    }

    @Test
    public void testGetAllOrders() {
        List<OrderDetails> orders = new ArrayList<>();
        OrderDetails order1 = new OrderDetails();
        order1.setOrderId(1L);
        OrderDetails order2 = new OrderDetails();
        order2.setOrderId(2L);
        orders.add(order1);
        orders.add(order2);

        when(orderRepo.findAll()).thenReturn(orders);

        List<OrderDetails> retrievedOrders = orderService.getAllOrders();

        assertEquals(orders, retrievedOrders);
    }

    @Test
    public void testRandomPaymentIdGenerator() {
        String paymentId = orderService.randomPaymentIdGenerator();

        assertEquals(17, paymentId.length());
    }

    @Test
    public void testGetOrderByPaymentId() {
        OrderDetails order = new OrderDetails();
        order.setPaymentId("C_O_D123456789012");

        when(orderRepo.findByPaymentId(anyString())).thenReturn(order);

        OrderDetails retrievedOrder = orderService.getOrderByPaymentId("C_O_D123456789012");

        assertEquals(order, retrievedOrder);
    }
}
