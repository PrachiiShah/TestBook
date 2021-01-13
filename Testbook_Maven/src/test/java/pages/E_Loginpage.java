/* Author: Prachi Shah
 * Date: 08-Jan-2020
 * Tests: Login Functionality
 * Description: Contains the methods for clicking on login button, sending user email,
 * 				user password, clicking on login, etc. Validations for valid and invalid
 * 				credentials is also included.	
 * 				For successful login, dashboard is displayed.
 * 				For unsuccessful login, error messages are displayed and validated.
 */

package pages;

import java.util.concurrent.TimeUnit;
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

public class E_Loginpage extends testBook_Base{
	
	//Login page elements.
	static @FindBy(name = "pswd") WebElement password_field;
	static @FindBy(id = "loginUsername") WebElement email_field;
	static @FindBy(id = "onBoardingIframe") WebElement login_frame;
	static @FindBy(xpath="//a[text()='Login']") WebElement login_button;
	static @FindBy(xpath = "//button[text()='Login']") WebElement user_login_button;
	static @FindBy(xpath = "//button[@ng-click='proceedToLogin(user)']") WebElement next_button;
	static @FindBy(xpath = "//div[@class = 'modal-body pad-0 js-inject-iframe']/div[2]") WebElement close_frame;
	static @FindBy(xpath = "//div[@data-error ='Please enter a valid email or mobile']") WebElement email_error;
	
	//Initialize asserts
	WebDriverWait wait = new WebDriverWait(driver, 30);
	SoftAssert softAssert = new SoftAssert();
	
	//Binds login page elements.
	public E_Loginpage(){
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Opens Testbook url
	public void openurl(){
		driver.get(prop.getProperty("url"));
		System.out.println("User directed to testbook website.");
		log = ext.createTest("Tesbook website display");
		log.log(Status.PASS, "User is displayed the website.");
		takescreenshot("Testbook_website.png");
	}
	
	//Clicks on login.
	public void click_login() throws Exception {
		try{
			Thread.sleep(15000);
			new Actions(driver).moveToElement(login_button).moveByOffset(30,30).click().perform();
			System.out.println("Popup disabled.");
			Assert.assertTrue(login_button.isDisplayed());
			login_button.click();
			System.out.println("User is on login page.");
			driver.switchTo().frame(login_frame);	
		}
		catch(Exception e) {
			System.out.println("Login button is not displayed.");
		}
	}
	
	//Email Input.
	public void enter_email(String email) throws Exception{
		try {
			wait.until(ExpectedConditions.visibilityOf(email_field));
			email_field.click();
			if(email.contains("@")) {
				email_field.clear();
				email_field.sendKeys(email);
				log = ext.createTest("Email Input");
				log.log(Status.PASS, "User successfully enters email.");
				takescreenshot("Enter_email.png");
				next_button.click();
				System.out.println("User enters email credentials.");
			}
			else {
				email_field.clear();
				email_field.sendKeys(email);
				log = ext.createTest("Invalid Email Input");
				log.log(Status.FAIL, "User enters invalid email.");
				takescreenshot("Enter_invalid_email.png");
				next_button.click();
				System.out.println("User enters invalid email credentials.");
				close_frame.click();
			}
		}
		catch(Exception e)
		{
			log = ext.createTest("Email Input");
			log.log(Status.FAIL, "User successfully enters email.");
			takescreenshot("Enter_email.png");
		}
	}
	
	public void enter_password(String password) throws Exception{
		try {
			wait.until(ExpectedConditions.visibilityOf(email_field));
			password_field.sendKeys(password);
			log = ext.createTest("Password Input");
			log.log(Status.PASS, "User successfully enters password.");
			takescreenshot("Enter_password.png");
			user_login_button.click();
			System.out.println("User enters password credentials.");
		}
		catch(Exception e)
		{
			log = ext.createTest("Password Input");
			log.log(Status.FAIL, "User successfully enters password.");
			takescreenshot("Enter_password.png");
		}
	}
	
	//Password Input
	public void validate_dashboard() throws Exception{
		String expectedOut = "Dashboard | Testbook";
		String actualOut = driver.getTitle();
		try {
			softAssert.assertEquals(actualOut, expectedOut);
				System.out.println("User is on dashboard after login.");
				log = ext.createTest("Dashboard Display");
				log.log(Status.PASS, "User was successfully logged in.");
				takescreenshot("Dashboard_page.png");
				System.out.println("------------------------------------------------------------------------------");
		}
		catch(Exception e) {
			softAssert.assertNotEquals(actualOut, expectedOut);
				System.out.println("User is not logged in.");
				log = ext.createTest("Dashboard Display");
				log.log(Status.FAIL, "User still on login page.");
				takescreenshot("Dashboard_page.png");
				System.out.println("------------------------------------------------------------------------------");
		}
	}
	
	//Validate Email Errors
	public void validate_email_error(){
		try {
			String error = email_error.getAttribute("data-error");
			softAssert.assertEquals(error, "Please enter a valid email or mobile");
				log = ext.createTest("Invalid email");
				log.log(Status.PASS, "Valid error message is shown.");	
				takescreenshot("Email_error");
				System.out.println("Error displayed: "+email_error.getAttribute("data-error"));
		}
		catch(Exception e) {
			log = ext.createTest("Invalid email");
			log.log(Status.FAIL, "Valid error message is not shown.");	
			takescreenshot("Email_error");
			System.out.println("Error not displayed");
		}
	}
	
	//Email Blank
	public void blank_email_input(){
		try {
			if(email_field.isDisplayed() == true) {
				email_field.click();
				Thread.sleep(1000);
				next_button.click();
			}
		}
		catch(Exception e) {}
	}
	
	//Password Blank
	public void blank_password_input(String email){
		try {
				email_field.click();
				email_field.sendKeys(email);
				Thread.sleep(1000);
				next_button.click();
				if(password_field.isDisplayed()) {
					Thread.sleep(1000);
					password_field.click();
					Thread.sleep(1000);
					login_button.click();
			}
		}
		catch(Exception e) {}
	}
	
	//Validate required fields
	public void validate_required(){
		try{
			if(email_field.getText().isEmpty()==true || password_field.getText().isEmpty()==true){
			log = ext.createTest("Blank details");
			log.log(Status.PASS, "Email or password left blank.");
			takescreenshot("Blank_input.png");
			System.out.println("Input fields left blank.\n		Required message is displayed.");
			}
		}
		catch(Exception e) {
			log = ext.createTest("Blank details");
			log.log(Status.FAIL, "Email or password left blank.");
			takescreenshot("Blank_input.png");
			System.out.println("Invalid required fields error message is displayed.");
		}
	}
}
