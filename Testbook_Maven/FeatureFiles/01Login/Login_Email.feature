#Author: ps242215@gmail.com
#Feature: Different login scenarios.
@Email_Login
Feature: Login using Email
	@Testbook_Page
	Scenario: Testbook Page
		Given user is on testbook site
		Then user clicks on login button
	@Valid_Email_Login
	Scenario Outline: Valid email login
		When user enters valid <email> valid <password> clicks login
		Then user is  redirected to homepage
		Examples:
		| email | password |
		| ps242215@gmail.com | Mprachi@22 |
	@Invalid_Email_Login 
	Scenario Outline: Invalid email login
		When enters invalid <email>, clicks next
		Then user is displayed invalid email error message
		Examples:
		| email |
		| prachi |
		| ps242215@ |
		| ps_234324 |
	@Incorrect_Password
	Scenario Outline: Incorrect password login
		When enters valid <email> invalid <password> clicks login
		Then user is displayed error message
		Examples:
		| email | password |
		| ps242215@gmail.com | dnsdew |
	@No_Email_Login
	Scenario: Login without email
		When user gives blank email clicks on next button
		Then user is displayed email required message
	@No_Password_Login
	Scenario Outline: Login without password
		When enters valid <email> blank password clicks next login
		Then user is displayed password blank message
		Examples:
		| email |
		| ps242215@gmail.com |


