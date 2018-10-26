package com.techmango.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.techmango.persistence.dao.mysql.AuthorRepository;
import com.techmango.persistence.dao.postgresql.UserRepository;
import com.techmango.persistence.entity.mysql.Author;
import com.techmango.persistence.entity.postgresql.User;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private UserRepository userRepository;

	private Long bookId = 1L;
	
	/**
	 * Will be executed before the methods annotated with code @Test.
	 */
	@Before
	public void init() {

		Author author = new Author();
		author.setId(1L);
		author.setFirstname("Radouane");
		author.setLastname("Roufid");

		authorRepository.save(author);

		User user = new User();
		user.setId(1);
		user.setName("Spring Boot Book");
		user.setEmail("aaa@gmail.com");

		userRepository.save(user);
	}

	/**
	 * Test should find the author's book from the PostgreSQL database.
	 */
	@Test
	public void testShouldFindAuthor() {
		
		User user = userRepository.findOne(bookId);
		
		Assert.assertNotNull(user);
		
		Author author = authorRepository.findOne(user.getId());
		
		Assert.assertNotNull(author);
		
		System.out.println("The book " + user.getName() + " was written by " + author.getFirstname() + " " + author.getLastname());
		
	}

}
