package com.techmango.persistence.dao.postgresql;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.techmango.persistence.entity.postgresql.User;


/**
 * Book repository.
 * 
 * @author Thennarasu Subramaniyan.
 *
 */
public interface UserRepository  extends CrudRepository<User, Long>{
    List<User> findByName(String lastName);
}
