package com.techmango.reseller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techmango.reseller.entity.Order;

@Transactional
@Repository
public class ResellerDAOImpl implements ResellerDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method is responsible to get all orders available in database and return it as List<Order>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders(){
		
		String sql = "FROM Order ";
		return (List<Order>) entityManager.createQuery(sql).getResultList();
	}

	/**
	 * This method is responsible to get a particular Order detail by given OrderId 
	 */
	@Override
	public Order getOrder(int orderId) {
		
		return entityManager.find(Order.class, orderId);
	}

	/**
	 * This method is responsible to create new Order in database
	 */
	@Override
	public Order createOrder(Order order) {
		entityManager.persist(order);
		Order b = getLastInsertedOrder();
		return b;
	}

	/**
	 * This method is responsible to update Order detail in database
	 */
	@Override
	public Order updateOrder(int orderId, Order order) {
		
		//First We are taking Order detail from database by given orderId and 
		// then updating detail with provided order object
		Order orderFromDB = getOrder(orderId);
		
		orderFromDB.setBulkOrderId(order.getBulkOrderId());
		orderFromDB.setBuyerId(order.getBuyerId());
		orderFromDB.setCreated(order.getCreated());
		orderFromDB.setCreatedAt(order.getCreatedAt());
		orderFromDB.setCreatedUserId(order.getCreatedUserId());
		orderFromDB.setCurrency(order.getCurrency());
		orderFromDB.setCustomerId(order.getCustomerId());
		orderFromDB.setDeleted(order.isDeleted());
		orderFromDB.setDiscountValue(order.getDiscountValue());
		orderFromDB.setNetValue(order.getNetValue());
		orderFromDB.setOrderLine(order.getOrderLine());
		orderFromDB.setOrderNo(order.getOrderNo());
		orderFromDB.setPoNumber(order.getPoNumber());
		orderFromDB.setRequesterId(order.getRequesterId());
		orderFromDB.setSalesOrgId(order.getSalesOrgId());
		orderFromDB.setShipToPartyId(order.getShipToPartyId());
		orderFromDB.setSoNumber(order.getSoNumber());
		orderFromDB.setSourceId(order.getSourceId());
		orderFromDB.setStatusId(order.getStatusId());
		orderFromDB.setSurchargeValue(order.getSurchargeValue());
		orderFromDB.setUpdated(order.getUpdated());
		orderFromDB.setUpdatedAt(order.getUpdatedAt());
		orderFromDB.setVersion(order.getVersion());
		
		entityManager.flush();
		
		//again i am taking updated result of order and returning the order object
		Order updatedOrder = getOrder(orderId);
		
		return updatedOrder;
	}

	/**
	 * This method is responsible for deleting a particular(which id will be passed that record) 
	 * record from the database
	 */
	@Override
	public boolean deleteOrder(int orderId){
		Order order = getOrder(orderId);
		entityManager.remove(order);
		
		//we are checking here that whether entityManager contains earlier deleted order or not
		// if contains then order is not deleted from DB that's why returning false;
		boolean status = entityManager.contains(order);
		if(status){
			return false;
		}
		return true;
	}
	
	/**
	 * This method will get the latest inserted record from the database and return the object of Order class
	 * @return Order
	 */
	private Order getLastInsertedOrder(){
		String sql = "from Order ";
		Query query = entityManager.createQuery(sql);
		query.setMaxResults(1);
		Order order = (Order)query.getSingleResult();
		return order;
	}

}
