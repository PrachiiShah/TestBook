/* Author: Prachi Shah
 * Tests: Login Functionality
 * Description: Contains the step definitions for executing login part.
 * 				Includes calling validation methods for both valid and 
 * 				invalid credentials is also included.	
 * 				For successful login, dashboard is displayed.
 * 				For unsuccessful login, error messages are displayed and validated.
 */
package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.E_Loginpage;

public class emailLoginSteps {
	E_Loginpage elogin = new E_Loginpage();

	 @Given("^user is on testbook site$")
		 public void user_is_on_testbook_site() throws Throwable {
			elogin.openurl(); 
			Thread.sleep(2000);
		 }
	 @Then("^user clicks on login button$")
	     public void user_clicks_on_login_button() throws Throwable {
	       elogin.click_login();
	     }

	 @When("^user enters valid (.+) valid (.+) clicks login$")
	     public void user_enters_valid_valid_clicks_login(String email, String password) throws Throwable {
	       elogin.enter_email(email);
	       Thread.sleep(1000);
	       elogin.enter_password(password);
	       Thread.sleep(1000);
	     }

	 @Then("^user is  redirected to homepage$")
	     public void user_is_redirected_to_homepage() throws Throwable {
	        elogin.validate_dashboard();
	     }
	
	 @When("^enters invalid (.+), clicks next$")
	 public void enters_invalid_clicks_next(String email) throws Throwable {
		 elogin.enter_email(email);
		 Thread.sleep(2000);
	 }
	 
	 @Then("^user is displayed invalid email error message$")
	 public void user_is_displayed_invalid_email_error_message() throws Throwable {
		 elogin.validate_email_error();
	 }

	 @When("^enters valid (.+) invalid (.+) clicks login$")
	 public void enters_valid_invalid_clicks_login(String email, String password) throws Throwable {
		 elogin.enter_email(email);
		 elogin.enter_password(password);
	 }

	 @Then("^user is displayed error message$")
	 public void user_is_displayed_error_message() throws Throwable {
		 elogin.validate_dashboard();
	 }
	 
	 @When("^user gives blank email clicks on next button$")
	 public void user_gives_blank_email_clicks_on_next_button() throws Throwable {
		 elogin.blank_email_input();
	 }

	 @Then("^user is displayed email required message$")
	 public void user_is_displayed_email_required_message() throws Throwable {
		 elogin.validate_required();
	 }
	 
	 @When("^enters valid (.+) blank password clicks next login$")
	 public void enters_valid_blank_password_clicks_next_login(String email) throws Throwable {
		 elogin.blank_password_input(email);
	 }

	 @Then("^user is displayed password blank message$")
	 public void user_is_displayed_password_blank_message() throws Throwable {
		 elogin.validate_required();
	 }
}
