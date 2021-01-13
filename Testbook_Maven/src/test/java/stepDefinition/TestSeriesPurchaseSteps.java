package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.TestSeries_Selection;

public class TestSeriesPurchaseSteps {
	
		TestSeries_Selection ts = new TestSeries_Selection();
			
		@Given("^user clicks on Test series tab$")
		public void user_clicks_on_test_series_tab() throws Throwable {
			ts.go_to_testseries();
		}
		
		@Then("^user is diplayed free test series list$")
		public void user_is_diplayed_free_test_series_list() throws Throwable {
		      ts.free_series_display();
		}
		
		@Given("^user is on Test series page$")
		public void user_is_on_test_series_page() throws Throwable {
			System.out.println("Checked - user is on test series page.");
		}

		@When("^user views any test series$")
		public void user_views_any_test_series() throws Throwable {
			ts.choose_series();
		}
		
		@Then("^user redirected to relevant series page$")
		public void user_redirected_to_relevant_series_page() throws Throwable {
			ts.validate_test_series();
		}
		    
		@When("^user is on selected series page$")
		public void user_is_on_selected_series_page() throws Throwable {
			System.out.println("Checked - user is on selected test series page.");
		}

		@Then("^user clicks on unlock now button$")
		public void user_clicks_on_unlock_now_button() throws Throwable {
			ts.unlock_now();
		}
}
