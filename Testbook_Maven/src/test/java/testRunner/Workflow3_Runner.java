package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\01Login\\Login_Email.feature",
				"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\03BillingAndPayment\\1_CourseSelect.feature",
				"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\03BillingAndPayment\\2_BuyPass.feature"} 					
		,glue={"stepDefinition","base"}									
		,tags= {"@Testbook_Page,@Valid_Email_Login,@Series_Display,@Series_Purchase,@BuyPassPlan,@Payment_methods_card"}											
		)

public class Workflow3_Runner extends AbstractTestNGCucumberTests {}
