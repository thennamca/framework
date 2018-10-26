package com.techmango.cucumber.stepdefs;


import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.techmango.cucumber.CucumberApplication;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "eureka.client.enabled=false")
@ContextConfiguration(classes = CucumberApplication.class)
public class HttpMethodStepDefs {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private ResponseEntity<String> response;
	
	private ResponseEntity<String> result;

	// Covers GET method
	@Given("invoking GET method on (.*)")
	public void invokingGetMethod(String url) {
		System.out.println("Url***:"+url);
		this.response = this.testRestTemplate.getForEntity(url, String.class);
	}

	@Then("^the response status for GET is (\\d+)$")
	public void getMethodResponseStatus(int status) throws Throwable {
		Assert.assertEquals(status, response.getStatusCode().value());
	}

	@Then("The get method reply with (.*)")
	public void getMethodResult(String msg) {
		System.out.println("Msg:"+msg);
		System.out.println("Response:"+response);
		Assert.assertEquals(msg, response.getBody());	    
	}

	// Covers POST method
	@Given("invoking POST method on (.*)")
	public void invokingPostMethod(String url) {
		System.out.println("Url***:"+url);
		this.response = this.testRestTemplate.postForEntity(url,String.class,String.class);
	}

	@Then("^the response status for POST is (\\d+)$")
	public void postMethodResponseStatus(int status) throws Throwable {
		Assert.assertEquals(status, response.getStatusCode().value());
	}

	@Then("the post method response with (.*)")
	public void postMethodResult(String msg) {
		System.out.println("Msg:"+msg);
		System.out.println("Response:"+response);
		Assert.assertEquals(msg, response.getBody());	    
	}

	// Covers PUT method
	@Given("invoking PUT method on (.*)")
	public void invokingPutMethod(String url) {
		System.out.println("Url***:"+url);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		result = testRestTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
	}
	
	@Then("^the response status for PUT is (\\d+)$")
	public void thePutResponseStatusIs(int status) throws Throwable {
		Assert.assertEquals(status, result.getStatusCode().value());
	}
	
	@Then("the put method reply with (.*)")
	public void the_put_method_response_body_must_contains_welcome(String msg) {
		Assert.assertEquals(msg, result.getBody());
	}
	
	// Covers DELETE method
	@Given("invoking delete method on (.*)")
	public void invokingDeleteMethod(String url) {
		System.out.println("Url***:"+url);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		     
		testRestTemplate.delete(url);
	}	

}
