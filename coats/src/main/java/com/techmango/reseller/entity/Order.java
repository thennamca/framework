package com.techmango.reseller.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coats_reseller_bulk_order")
public class Order implements Serializable{

	private static final long serialVersionUID = -3238853654124618158L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="bulk_order_id")
	private int bulkOrderId;
	
	@Column(name="order_line")
	private int orderLine;
	
	@Column(name="source_id")
	private int sourceId;
	
	@Column(name="order_no")
	private String orderNo;
	
	@Column(name="sales_org_id")
	private int salesOrgId;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="requester_id")
	private int requesterId;
	
	@Column(name="ship_to_party_id")
	private int shipToPartyId;
	
	@Column(name="buyer_id")
	private int buyerId;
	
	@Column(name="po_number")
	private String poNumber;
	
	@Column(name="surcharge_value")
	private String surchargeValue;
	
	@Column(name="discount_value")
	private String discountValue;
	
	@Column(name="net_value")
	private String netValue;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="status_id")
	private int statusId;
	
	@Column(name="so_number")
	private int soNumber;
	
	@Column(name="created")
	private String created;
	
	@Column(name="created_user_id")
	private int createdUserId;
	
	@Column(name="updated")
	private String updated;
	
	@Column(name="deleted")
	private boolean deleted;
	
	@Column(name="version")
	private String version;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="updated_at")
	private Timestamp updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBulkOrderId() {
		return bulkOrderId;
	}

	public void setBulkOrderId(int bulkOrderId) {
		this.bulkOrderId = bulkOrderId;
	}

	public int getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(int orderLine) {
		this.orderLine = orderLine;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(int salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}

	public int getShipToPartyId() {
		return shipToPartyId;
	}

	public void setShipToPartyId(int shipToPartyId) {
		this.shipToPartyId = shipToPartyId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getSurchargeValue() {
		return surchargeValue;
	}

	public void setSurchargeValue(String surchargeValue) {
		this.surchargeValue = surchargeValue;
	}

	public String getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}

	public String getNetValue() {
		return netValue;
	}

	public void setNetValue(String netValue) {
		this.netValue = netValue;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getSoNumber() {
		return soNumber;
	}

	public void setSoNumber(int soNumber) {
		this.soNumber = soNumber;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	

}
