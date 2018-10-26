package com.techmango.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="grants")
public class Grant extends BaseModel {
	
	private static final long serialVersionUID = 4192997147639777673L;

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
