package testRunner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"FeatureFiles/01Login/Login_Email.feature"} 					// which feature files to run
		,glue={"stepDefinition","base"}									//package names having the scripts for the feature file
		,tags= {"@Testbook_Page,@Valid_Email_Login"}											// the tags to run.... which are in feature file		
		)

public class Email_Test_Runner extends AbstractTestNGCucumberTests{
}
