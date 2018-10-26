Feature: Cucumber Test case for http methods

@GetMethod
  Scenario: Invoking GET method
    Given invoking GET method on /cucumber/getInfo
    Then the response status for GET is 200
    And The get method reply with Hello cucumber!
    
@PostMethod 
 Scenario: Invoking POST method
 	Given invoking POST method on /cucumber/postContent?text=welcome
 	Then the response status for POST is 200
 	And the post method response with welcome
 	
@PutMethod
 Scenario: Invoking PUT method
 	Given invoking PUT method on /cucumber/putContent/158
 	Then the response status for PUT is 200
 	And the put method reply with 158
 	
@DeleteMethod
Scenario: Invoking DELETE method
 	Given invoking delete method on /cucumber/deleteContent/250
 	
 	
 	
    