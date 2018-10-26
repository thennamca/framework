package com.techmango.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmango.auth.entity.User;
import com.techmango.auth.service.CustomUserDetailsService;

@RestController
@RequestMapping("/user")
public class UserController  {
	@Autowired
	CustomUserDetailsService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('role_admin')")
    public void create(@RequestBody User user) {
    	userService.save(user);
    }

    @RequestMapping(value = "/{id}") 
    public User read(@PathVariable long id) {
		return null;
       // return userService.findOne(id);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('role_user', 'role_admin')")
    public List<User> readAll() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('role_admin')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
    	userService.save(user);
    }

    @PreAuthorize("hasRole('role_admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable long id) {
    	userService.delete(id); 
    }

}
