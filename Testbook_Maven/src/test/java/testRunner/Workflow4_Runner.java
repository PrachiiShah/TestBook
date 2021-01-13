/* Author: Prachi Shah
 * Date: 13-Jan-2020
 * Tests: Live Class View Functionality
 * Description: Contains the runner class for executing the view live classes functionality.
 */

package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//Runs the entire scenario from login upto viewing a live class.
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"FeatureFiles/01Login/Login_Email.feature","FeatureFiles/04LiveClasses/ClassDisplay.feature"} 					
		,glue={"stepDefinition","base"}									
		,tags= {"@Testbook_Page,@Valid_Email_Login,@LiveClassList"}												
		)

public class Workflow4_Runner extends AbstractTestNGCucumberTests{

}
