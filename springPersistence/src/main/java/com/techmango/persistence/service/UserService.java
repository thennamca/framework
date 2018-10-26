package com.techmango.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmango.persistence.dao.postgresql.UserRepository;
import com.techmango.persistence.entity.postgresql.User;


@Service
public class UserService {
	@Autowired
    UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
		
	}

	public User findOne(long id) {
		return userRepository.findOne(id);
	}

	public void delete(long id) {
		userRepository.delete(id); 
		
	}

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
}
