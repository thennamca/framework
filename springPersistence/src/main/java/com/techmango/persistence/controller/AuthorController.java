package com.techmango.persistence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmango.persistence.dao.mysql.AuthorRepository;
import com.techmango.persistence.entity.mysql.Author;

@RestController
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	AuthorRepository authorRepository;
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Author author) {
		authorRepository.save(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) 
    public Author read(@PathVariable long id) {
        return authorRepository.findOne(id);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET ) 
    public List<Author> readAll() {
        return (List<Author>) authorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Author author) {
    	authorRepository.save(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable long id) {
    	authorRepository.delete(id); 
    }
}
