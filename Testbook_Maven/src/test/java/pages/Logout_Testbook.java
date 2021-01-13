/* Author: Prachi Shah
 * Date: 13-Jan-2020
 * Tests: Logout Functionality
 * Description: Contains the methods for clicking on logout after the workflow has completed.
 */

package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import base.testBook_Base;

public class Logout_Testbook extends testBook_Base{
	
	static @FindBy(xpath = "(//img[@class='circle-img'])[2]") WebElement dropdown;
	static @FindBy(xpath = "(//li[@class='dropdown-item ng-scope'])[8]/a") WebElement logout_option;
	
	public Logout_Testbook() {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void logout()											// clicks on profile button and clicks on settings.
	{
		try{
			dropdown.click();
			System.out.println("Clicking on profile button............");
			logout_option.click();
			System.out.println("Clicking on logout............");
			log = ext.createTest("Click Logout");
			log.log(Status.PASS, "User clicks on profile -> logout option.");
			takescreenshot("Logout_button.png");			
		}
		catch(Exception e) {
			System.out.println("Profile button is not displayed............");
			log = ext.createTest("Click Profile Button");
			log.log(Status.FAIL, "Profile button is not displayed.");
			takescreenshot("Logout_button.png");
		}	
	}

}
