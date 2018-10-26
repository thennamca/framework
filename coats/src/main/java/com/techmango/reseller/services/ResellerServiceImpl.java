package com.techmango.reseller.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmango.reseller.dao.ResellerDAO;
import com.techmango.reseller.entity.Order;

@Service
public class ResellerServiceImpl implements ResellerService {

	@Autowired
	private ResellerDAO dao;

	@Override
	public List<Order> getOrders() {
		return dao.getOrders();
	}

	@Override
	public Order createOrder(Order order) {
		return dao.createOrder(order);
	}

	@Override
	public Order updateOrder(int orderId, Order order) {
		return dao.updateOrder(orderId, order);
	}

	@Override
	public Order getOrder(int orderId) {
		return dao.getOrder(orderId);
	}

	@Override
	public boolean deleteOrder(int orderId) {
		return dao.deleteOrder(orderId);
	}

}
