/* Author: Prachi Shah
 * Date: 12-Jan-2020
 * Tests: Login->Course Purchase functionality
 * Description: Contains the runner class for executing the workflow in a sequence as:
 * 				1. Valid user login
 * 				2. Course tab navigation -> choose course -> click get testbook pass
 * 				3. Choose plan -> choose payment method -> enter valid details -> Click purchase	
 */

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
		,tags= {"@Testbook_Page,@Valid_Email_Login,@Course_Display,@Course_Purchase,@BuyPassPlan,@Payment_methods_upi"}											
		)

public class Workflow2_Runner extends AbstractTestNGCucumberTests{
}
