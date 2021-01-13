#Author: ps242215@gmail.com
@Forgot_password
Feature: Forgot Password Reset
	@Forgotpswd_Display
	Scenario Outline: Forgot password page display
		When user clicks on login button
		And enters valid <email>, clicks next
		And clicks forgot password
		Then user redirected to forgot password page
		Examples:
		| email |
		| ps242215@gmail.com |
	@Reset_Password
	Scenario Outline: Forgot password using email
		When user clicks on login button, enters valid <email>, clicks next
		And clicks forgot password
		And enters registered <email>, clicks reset password
		Then user displayed email shared message
		Examples:
		| email |
		| ps242215@gmail.com |

