/* Author: Prachi Shah
 * Date: 11-Jan-2020
 * Tests: Login->Settings->Cousre Purchase functionality
 * Description: Contains the runner class for executing the workflow in a sequence as:
 * 				1. Valid user login
 * 				2. Go to settings -> upload profile pic -> update user details -> save profile
 * 				3. Course tab navigation -> choose course -> click get testbook pass
 * 				4. Choose plan -> choose payment method -> enter valid details -> Click purchase	
 */

package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\01Login\\Login_Email.feature",
				"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\02ProfileUpdate\\Settings.feature",
				"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\03BillingAndPayment\\1_CourseSelect.feature",
				"C:\\Users\\coolp\\git\\repository2\\Testbook_Maven\\FeatureFiles\\03BillingAndPayment\\2_BuyPass.feature"} 			
		,glue={"stepDefinition","base"}								
		,tags= {"@Testbook_Page,@Valid_Email_Login,@Settings_Page,@Profile_Update,@Details_Update,@Course_Display,@Course_Purchase,@BuyPassPlan,@Payment_methods_upi"}											// the tags to run.... which are in feature file		
		)
public class Workflow1_Runner extends AbstractTestNGCucumberTests {

}
