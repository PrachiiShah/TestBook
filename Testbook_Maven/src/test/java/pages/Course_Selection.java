package pages;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import base.testBook_Base;

public class Course_Selection extends testBook_Base{
	static @FindBy(xpath = "//a[@class='nav-icon-text courses js-header-link']") WebElement courses_tab;
	static @FindBy(id = "coursesInfoNav") WebElement course_nav;
	static @FindBy(xpath = "//div[@class='courses ui-card courses--offset']") WebElement course_section;
	static @FindBy(xpath = "//h1[@class='main-banner__heading ng-binding']") WebElement course_heading;
	static @FindBy(xpath = "//div[@class='courses ui-card courses--offset']/div[1]/div[1]/a") WebElement my_course;
	static @FindBy(xpath = "//h3[text()='Analytical Reasoning for Banking Course Curriculum']") WebElement course_content;
	static @FindBy(xpath = "//a[@class='btn ui-btn w-100 btn-success ng-scope ng-binding']") WebElement click_getpass;
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	SoftAssert softAssert = new SoftAssert();
	
	public Course_Selection() {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void go_to_courses() {
		try {
			wait.until(ExpectedConditions.visibilityOf(courses_tab));
			courses_tab.click();
			log = ext.createTest("Course tab navigation");
			log.log(Status.PASS, "Clicked on course tab.");
			takescreenshot("Course_tab_click.png");
			Thread.sleep(1000);
			System.out.println("User able to click on course tab.");
		}
		catch(Exception e) {
			log = ext.createTest("Course tab navigation");
			log.log(Status.FAIL, "Cannot click on course tab.");
			takescreenshot("Course_tab_click.png");
			System.out.println("User not able to click on course tab.");
		}
	}
	
	public void choose_course(String course) {
		try {
			if(course.matches("banking")) {
				course_nav.findElement(By.xpath("//input[@placeholder='Search Courses']")).sendKeys(course);
				new Actions(driver).click().perform();
				log = ext.createTest("Get_preferred_course");
				log.log(Status.PASS, "User specific course selected.");
				takescreenshot("User_search_course.png");
				System.out.println("User preferred course is searched.");
				Thread.sleep(1000);
				my_course.click();
				System.out.println("User's specific course is chosen.");
			}
			else {
				System.out.println("User preferred course is not searched.");
			}			
		}
		catch(Exception e) {
			log = ext.createTest("Get_preferred_course");
			log.log(Status.FAIL, "User specific course not selected.");
			takescreenshot("User_search_course.png");
			System.out.println("User's specific course is not chosen.");
		}
	}
	
	public void validate_course() {
		Boolean heading = course_heading.getText().isEmpty();
		try {
			Assert.assertNotNull(heading);
				System.out.println(course_heading.getText());
				log = ext.createTest("My_course");
				log.log(Status.PASS, "User's course openned.");
				takescreenshot("Open_user_course.png");
				System.out.println("User can view his course.");
				System.out.println("-------------------------------------------------------------------------");
		}
		catch(Exception e) {
			log = ext.createTest("My_course");
			log.log(Status.FAIL, "User's course not openned.");
			takescreenshot("Open_user_course.png");
			System.out.print("User cannot view his course.");
			System.out.println("-------------------------------------------------------------------------");
		}
	}
	
	public void get_testbook_pass() {
		try {
			wait.until(ExpectedConditions.visibilityOf(click_getpass));
				click_getpass.click();
				log = ext.createTest("GetPass is displayed");
				log.log(Status.PASS, "User is able to click on the get pass button.");
				takescreenshot("Get_Pass.png");
				Thread.sleep(1000);
				System.out.println("User is able to click on the Get Pass option.");
				System.out.println("---------------------------------------------------------------------------");
		}
		catch(Exception e) {
			log = ext.createTest("GetPass is displayed");
			log.log(Status.FAIL, "User is not able to click on the get pass button.");
			takescreenshot("Get_Pass.png");
			System.out.println("User is not able to click on the Get Pass option.");
			System.out.println("---------------------------------------------------------------------------");
		}	
	}
}
