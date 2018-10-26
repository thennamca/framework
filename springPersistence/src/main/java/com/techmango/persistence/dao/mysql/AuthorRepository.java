package com.techmango.persistence.dao.mysql;

import org.springframework.data.repository.CrudRepository;

import com.techmango.persistence.entity.mysql.Author;

/**
 * Author Repository.
 * 
 * @author Thennarasu Subramaniyan.
 *
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
