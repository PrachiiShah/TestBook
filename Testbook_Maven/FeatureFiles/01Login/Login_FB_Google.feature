#Author: ps242215@gmail.com
@API_Login
Feature: Login_FB_Google
	@Testbook_Page
	Scenario: Testbook Page
		Given user is on testbook site
	@Fb_Login
  Scenario: Login with facebook 
    Given user clicks on fb login button 
    When user gives valid fb credentials #<fb_id> valid <fb_pswd>
    Then user redirected to homepage
  @Gmail_Login
  Scenario: Login with google
		Given user clicks on google login button 
    When user gives valid gmail credentials #<fb_id> valid <fb_pswd>
    Then user redirected to homepage
		