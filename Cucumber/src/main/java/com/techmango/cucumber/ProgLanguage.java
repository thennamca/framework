package com.techmango.cucumber;

import java.io.Serializable;

/**
 * Sample Model object for Redis Cache
 * 
 * @author techmango
 *
 */
public class ProgLanguage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
