package com.techmango.cucumber;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;



import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty",tags="@GetMethod")
public class GetMethodTest {
	
	List<String> test = new ArrayList<>();
}
