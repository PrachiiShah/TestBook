/* Author: Prachi Shah
 * Date: 13-Jan-2020
 * Tests: Live Class View Functionality
 * Description: Contains the step definitions for calling the pages
 * 				methods to execute the live class view workflow.
 */
package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.LiveClasses;

public class LiveClassesSteps {
	
	 LiveClasses lc = new LiveClasses();
	 
	 @Given("^user clicks on Live Classes Tab$")
	 public void user_clicks_on_live_classes_tab() throws Throwable {
		 lc.live_tab();
	 }

	 @When("^user select any live class$")
	 public void user_select_any_live_class() throws Throwable {
		 lc.sel_video();
	 }

	 @Then("^live video is displayed$")
	 public void live_video_is_displayed() throws Throwable {
		 lc.check_redirection();
	 }

}
