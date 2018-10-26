package com.techmango.resource.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techmango.resource.model.Grant;


public interface BaseDao<T extends Grant> extends JpaRepository<T, Serializable> {

}
