package com.techmango.resource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techmango.resource.model.Grant;
import com.techmango.resource.repository.BaseDao;


public class GenericRestController <T extends Grant>  {
	
	@Autowired
	private BaseDao<T> dao;
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('role_admin')")
	public List<T> list() {
		return dao.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('role_admin')")
	public T create(@RequestBody T entity) {
		return dao.save(entity);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('role_admin')")
	public T update(@PathVariable(value = "id") long id, @RequestBody T entity) {
		return dao.save(entity);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('role_admin')")
	public void delete(@PathVariable(value = "id") int id) {
		//dao.delete(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('role_admin')")
	public T get(@PathVariable(value = "id") int id) {
		return dao.getOne(id);
	}

}
