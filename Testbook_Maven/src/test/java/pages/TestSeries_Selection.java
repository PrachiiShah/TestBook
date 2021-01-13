package pages;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import base.testBook_Base;

public class TestSeries_Selection extends testBook_Base{
	static @FindBy(xpath = "//li[@class='nav-item tut-test-series']") WebElement testseries_tab;
	static @FindBy(xpath = "//h4[@title = 'SSC CGL Mock Test 2020']") WebElement my_test_series;
	static @FindBy(xpath = "//a[@ng-href='/ssc-cgl-exam/test-series']") WebElement view_test_series;
	static @FindBy(xpath = "//h1[text()='SSC CGL Mock Test 2020']") WebElement test_series_heading;
	static @FindBy(xpath = "//h4[@title = 'General Awareness Chapter Test 1']") WebElement test_1;
	static @FindBy(xpath = "//a[@title = 'Unlock General Awareness Chapter Test 1']") WebElement click_unlockNow;
	static @FindBy(xpath = "//div[@class = 'ng-isolate-scope']") WebElement free_row;
	WebDriverWait wait = new WebDriverWait(driver, 10);
	SoftAssert softAssert = new SoftAssert();
	
	public TestSeries_Selection() {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void go_to_testseries() {
		try {
			wait.until(ExpectedConditions.visibilityOf(testseries_tab));
			testseries_tab.click();
			log = ext.createTest("Testseries tab navigation");
			log.log(Status.PASS, "Clicked on test series tab.");
			takescreenshot("TestSeries_tab_click.png");
			System.out.println("User able to click on test series tab.");
		}
		catch(Exception e) {
			log = ext.createTest("Testseries tab navigation");
			log.log(Status.FAIL, "Cannot click on test series tab.");
			takescreenshot("TestSeries_tab_click.png");
			System.out.println("User not able to click on test series tab.");
		}
	}
	
	public void free_series_display() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,900)");
			List<WebElement> free_series = free_row.findElements(By.xpath("//div[@class='test-title text-truncate ng-binding']"));
			for(int i=0;i<free_series.size();i++) {
				String value = free_series.get(i).getText();
				System.out.println("	"+(i+1)+"."+value);		
			}
			log = ext.createTest("Free Series Display");
			log.log(Status.PASS, "Free test series displayed to user.");
			takescreenshot("Free_Series.png");
			System.out.println("Free series are displayed to user.");
			System.out.println("-------------------------------------------------------------------------------");
		}
		catch(Exception e) {
			log = ext.createTest("Free Series Display");
			log.log(Status.PASS, "Free test series displayed to user.");
			takescreenshot("Free_Series.png");
			System.out.println("No free series displayed.");
		}
	}
	
	public void choose_series() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(900,0)");
			String series = my_test_series.getText();
			softAssert.assertEquals(series, "SSC CGL Mock Test 2020");
				System.out.println("User's specific test series is chosen."+my_test_series.getText());
				view_test_series.click();
				log = ext.createTest("View test series");
				log.log(Status.PASS, "User specific test series selected.");
				takescreenshot("User_test_series.png");
		}
		catch(Exception e) {
			log = ext.createTest("View test series");
			log.log(Status.FAIL, "User specific test series not selected.");
			takescreenshot("User_test_series.png");
			System.out.println("User's specific test series is not chosen.");
		}
	}
	
	public void validate_test_series() {
		try {
			Boolean series_heading = test_series_heading.getText().isEmpty();
			softAssert.assertNotNull(series_heading);
				System.out.println(test_series_heading.getText());
				log = ext.createTest("My test series");
				log.log(Status.PASS, "User's test series openned.");
				takescreenshot("Open_test_series.png");
				System.out.println("User can view his test series.");
				System.out.println("-------------------------------------------------------------------------");
		}
		catch(Exception e) {
			log = ext.createTest("My test series");
			log.log(Status.FAIL, "User's test series not openned.");
			takescreenshot("Open_test_series.png");
			System.out.println("User cannot view his test series.");
			System.out.println("-------------------------------------------------------------------------");
		}
	}
	
	public void unlock_now() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,800)");
			String test_name = test_1.getText();
			softAssert.assertEquals(test_name, "Unlock General Awareness Chapter Test 1");
				click_unlockNow.click();
				log = ext.createTest("Unlock now button displayed");
				log.log(Status.PASS, "User is able to click on the unlock now button.");
				takescreenshot("Unlock_Now.png");
				System.out.println("User is able to click on the Unlock Now option.");
				System.out.println("---------------------------------------------------------------------------");
		}
		catch(Exception e) {
			log = ext.createTest("Unlock now button displayed");
			log.log(Status.FAIL, "User is unable to click on the unlock now button.");
			takescreenshot("Unlock_Now.png");
			System.out.println("User is not displayed the Unlock Now button.");
			System.out.println("---------------------------------------------------------------------------");
		}
	}
}
