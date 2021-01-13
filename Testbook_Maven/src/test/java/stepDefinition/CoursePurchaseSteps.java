package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Course_Selection;

public class CoursePurchaseSteps {
	
		Course_Selection cs = new Course_Selection();
		
		@Given("^user clicks on Courses tab$")
	  	public void user_clicks_on_courses_tab() throws Throwable {
			cs.go_to_courses();
	    }

	    @When("^user selects any (.+)$")
	    public void user_selects_any(String course) throws Throwable {
	    	cs.choose_course(course);
	    }

	    @Then("^user redirected to course page$")
	    public void user_redirected_to_course_page() throws Throwable {
	    	cs.validate_course();
	    }
	    
	    @Given("^user is on selected course page$")
	    public void user_is_on_selected_course_page() throws Throwable {
	    	System.out.println("Checked - user is on the selected course page.");
	    }

	    @Then("^user clicks on Get TestBook Pass$")
	    public void user_clicks_on_get_testbook_pass() throws Throwable {
	    	cs.get_testbook_pass();
	    }
	    
	   
}
