package com.techmango.reseller.services;

import java.util.List;

import com.techmango.reseller.entity.Order;

public interface ResellerService {

	List<Order> getOrders();

	Order getOrder(int orderId);

	Order createOrder(Order order);

	Order updateOrder(int orderId, Order order);

	boolean deleteOrder(int orderId);

}
