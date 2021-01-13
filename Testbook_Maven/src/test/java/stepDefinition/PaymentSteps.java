package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Buy_Pass;
import pages.Logout_Testbook;

public class PaymentSteps {
	
		Buy_Pass bp = new Buy_Pass();
		Logout_Testbook lt = new Logout_Testbook();
	 	
		@Given("^all plans are displayed$")
	    public void all_plans_are_displayed() throws Throwable {
	    	bp.plan_display();
	    }

	    @When("^user clicks on Buy Pass$")
	    public void user_clicks_on_buy_pass() throws Throwable {
	        bp.plan_choose();
	        bp.click_buy_pass();
	    }

	    @Then("^payment methods are displayed$")
	    public void payment_methods_are_displayed() throws Throwable {
	    	bp.validate_payment_method_displayed();
	    }
	    
	    @Given("^user selects upi payment$")
	    public void user_selects_upi_payment() throws Throwable {
	        bp.select_upi_payment();
	    }

	    @When("^user enters upi id$")
	    public void user_enters_upi_id() throws Throwable {
	       bp.add_upi_id();
	    }

	    @Then("^clicks on pay gets error message$")
	    public void clicks_on_pay_gets_error_message() throws Throwable {
	        bp.validate_upi_payment_status();
	        Thread.sleep(2000);
	        lt.logout();
	    }
	    
	    @Given("^user selects card payment$")
	    public void user_selects_card_payment() throws Throwable {
	        bp.select_card_payment();
	    }

	    @When("^user enters card details$")
	    public void user_enters_card_details() throws Throwable {
	        bp.add_card_details();
	    }

	    @Then("^clicks on pay$")
	    public void clicks_on_pay() throws Throwable {
	        bp.validate_card_payment_status();  
	        Thread.sleep(2000);
	        lt.logout();
	    }

}
