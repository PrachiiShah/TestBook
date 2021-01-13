package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//for running purchase course workflow

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\01Login\\Login_Email.feature",
				"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\03BillingAndPayment\\1_CourseSelect.feature",
				"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\03BillingAndPayment\\2_BuyPass.feature"} 					// which feature files to run
		,glue={"stepDefinition","base"}									//package names having the scripts for the feature file
		,tags= {"@Testbook_Page,@Valid_Email_Login,@Course_Display,@Course_Purchase,@BuyPassPlan,@Payment_methods_upi"}											// the tags to run.... which are in feature file
		)

public class Workflow2_Runner extends AbstractTestNGCucumberTests{
}
