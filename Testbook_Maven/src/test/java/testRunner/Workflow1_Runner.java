package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

// for updating profile of user

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"FeatureFiles/01Login/Login_Email.feature","FeatureFiles/02ProfileUpdate/Settings.feature","FeatureFiles/03BillingAndPayment/1_CourseSelect.feature","FeatureFiles/03BillingAndPayment/2_BuyPass.feature"} 					// which feature files to run
		,glue={"stepDefinition","base"}									//package names having the scripts for the feature file
		,tags= {"@Testbook_Page,@Valid_Email_Login,@Settings_Page,@Profile_Update,@Details_Update,@Course_Display,@Course_Purchase,@BuyPassPlan,@Payment_methods_upi"}											// the tags to run.... which are in feature file		
		)
public class Workflow1_Runner extends AbstractTestNGCucumberTests {

}
