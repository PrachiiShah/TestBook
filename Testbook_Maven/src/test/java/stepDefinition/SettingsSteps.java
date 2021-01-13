package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Settings_Page;

public class SettingsSteps {
		Settings_Page sp = new Settings_Page();
			
		@Given("^user is on homepage$")
	    public void user_is_on_homepage() throws Throwable {
			System.out.println("Checked - user is on Dashboard page.");
	    }
	    @When("^user clicks on dropdown settings$")
	    public void user_clicks_on_dropdown_settings() throws Throwable {
	    	sp.click_action_button();
	    	Thread.sleep(2000);
	    }
	    @Then("^user redirected to settings page$")
	    public void user_redirected_to_settings_page() throws Throwable {
	    	sp.validate_settings_page();
	    }
	    @Given("^user on settings page$")
	    public void user_on_settings_page() throws Throwable {
	    	System.out.println("Checked - user is on settings page.");
	    }

	    @When("^user clicks on change pic$")
	    public void user_clicks_on_change_pic() throws Throwable {
	        sp.click_change_profile();
	    }

	    @Then("^updates valid file user profile pic should be updated$")
	    public void updates_valid_file_user_profile_pic_should_be_updated() throws Throwable {
	        sp.upload_profile_pic();
	        Thread.sleep(2000);
	    }

	    @When("^user gives details clicks save profile$")
	    public void user_gives_details_clicks_save_profile() throws Throwable {
	    	sp.edit();
	    	Thread.sleep(1000);
	        sp.upload_education();
	        Thread.sleep(1000);
	        sp.upload_dob();
	        sp.upload_category();
	        Thread.sleep(1000);
	        sp.upload_location();
	        Thread.sleep(1000);
	        sp.upload_input_lang();
	        Thread.sleep(1000);
	        sp.save_profile();
	    }

	    @Then("^user profile should be updated$")
	    public void user_profile_should_be_updated() throws Throwable {
	        sp.validate_updates();
	    }
}
