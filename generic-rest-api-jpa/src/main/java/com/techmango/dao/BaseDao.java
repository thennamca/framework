package com.techmango.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.techmango.models.BaseModel;


@NoRepositoryBean
public interface BaseDao<T extends BaseModel> extends JpaRepository<T, Serializable> {

}
