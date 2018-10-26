package com.techmango.persistence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmango.persistence.entity.postgresql.User;
import com.techmango.persistence.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController  {
	@Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody User user) {
    	userService.save(user);
    }

    @RequestMapping(value = "/{id}") 
    public User read(@PathVariable long id) {
        return userService.findOne(id);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET) 
    public List<User> readAll() {
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
    	userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable long id) {
    	userService.delete(id); 
    }

}
