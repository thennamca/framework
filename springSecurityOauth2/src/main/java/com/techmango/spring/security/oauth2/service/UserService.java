package com.techmango.spring.security.oauth2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.techmango.spring.security.oauth2.model.security.User;
import com.techmango.spring.security.oauth2.repository.UserRepository;



@Service
public class UserService {
	
   @Autowired
   UserRepository userRepository;
   
   
   @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
	public void save(User user) {
		userRepository.save(user);
		
	}
   @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
	public User findOne(long id) {
		return userRepository.findOne(id);
	}
   @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
	public void delete(long id) {
		userRepository.delete(id); 
		
	}
   @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
}
