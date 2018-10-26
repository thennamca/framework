package com.techmango.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.techmango.auth.entity.User;
import com.techmango.auth.repository.UserRepository;

/**
 * 
 * @author Thennarasu
 *
 */
@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService  {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String input) {
		User user = null;

		if (input.contains("@"))
			user = userRepository.findByEmail(input);
		else
			user = userRepository.findByUsername(input);

		if (user == null)
			throw new BadCredentialsException("Bad credentials");

		new AccountStatusUserDetailsChecker().check(user);

		return user;
	}
	
	public List findAll() {
		@SuppressWarnings("rawtypes")
		List list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	public void delete(long id) {
		userRepository.deleteById(id);
	}

	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

    public User save(User user) {
	    User newUser = new User();
	    /*newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));*/
        return userRepository.save(newUser);
    }

}
