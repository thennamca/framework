package com.techmango.resource.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="grants")
public class Grant implements Serializable  {
	
	private static final long serialVersionUID = 4192997147639777673L;

	@Id  
	@Column(name="id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="grant_name")
	private String name;
	
	@Column(name="grant_key")
	private String grantKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrantKey() {
		return grantKey;
	}

	public void setGrantKey(String grantKey) {
		this.grantKey = grantKey;
	}
	
	
}
