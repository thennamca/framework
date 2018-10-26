package com.techmango.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techmango.auth.entity.User;

/**
 * 
 * @author Thennarasu
 * 
 *         UserRepository with custom methods for finding a User by username or
 *         email
 *
 */

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);

}
