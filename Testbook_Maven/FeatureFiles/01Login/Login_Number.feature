#Author: ps242215@gmail.com
@Mobile_Login
Feature: Login using Mobile
	@Testbook_Page
	Scenario: Testbook Page
		Given user is on testbook site
	@Valid_Email_Login
	Scenario Outline: Valid mobile login
		Given user clicks on login button
		When user enters valid <mobile> valid otp clicks login
		Then user is  redirected to homepage
		Examples:
		| mobile |
		| 7976719387 |
	@Invalid_Mobile_Login 
	Scenario Outline: Invalid mobile login
		Given user clicks on login button
		When enters invalid <mobile>, clicks next
		Then user is displayed invalid mobile_no error message
		Examples:
		| mobile |
		| 7001230449 |
		| 797671937 |
		| 7976793872 |
		| 7976719abc |
		| 79767_2144 |
		#email / password parameterized
	@Incorrect_otp
	Scenario Outline: Incorrect otp login
		Given user clicks on login button
		When enters valid <mobile> invalid <otp> clicks login
		Then user is displayed error message
		Examples:
		| mobile | otp |
		| 7976719387 | 12345 |
		| 7976719387 | 1234567 |
		| 7976719387 | asffgg |
		| 7976719387 | 2312_2 |
	@No_Email_Login
	Scenario: Login without email
		Given user clicks on login button
		When clicks on next button
		Then user is displayed email required message
	@No_Password_Login
	Scenario Outline: Login without password
		Given user clicks on login button
		When enters valid <email> clicks next login
		Then user is displayed password blank message
		Examples:
		| email |
		| ps242215@gmail.com |