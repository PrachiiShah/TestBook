/* Author: Prachi Shah
 * Date: 13-Jan-2020
 * Tests: Live Class View Functionality
 * Description: Contains the methods for going on the live classes tab,
 * 				choosing a free live class and validating if the user specific
 * 				live class is running or not.
 */

package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import base.testBook_Base;

public class LiveClasses extends testBook_Base
{
	//Live Class Elements
	static @FindBy(xpath="//a[@class='nav-icon-text study-lessons js-header-link']") WebElement live_tab;
	static @FindBy(xpath="//input[@placeholder='Search Videos']") WebElement search;
	static @FindBy(xpath="//div[contains(text(),'SSC Result Dates Out')]") WebElement video;
	static @FindBy(xpath="//h1[@class='video-title mt--0 mb-0 ng-binding']") WebElement tag;
	
	//Binds live class elements.
	public LiveClasses(){
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Live tab is clicked.
	public void live_tab(){
		try{
			if(live_tab.isDisplayed()){
			live_tab.click();
			System.out.println("Live Classes Tab is clicked.");
			}
		}
		catch(Exception e) {
			System.out.println("Unable to click on live classes tab.");
		}
	}
	
	//Video selected.
	public void sel_video(){
		try {
			search.sendKeys("SSC");
			Thread.sleep(2000);
			video.click();
			log = ext.createTest("Live_class_select");
			log.log(Status.PASS, "Searched for live class and selected.");
			takescreenshot("User_live_class.png");
			System.out.println("Live class is selected.");
		}
		catch(Exception e) {
			log = ext.createTest("Live_class_select");
			log.log(Status.FAIL, "Searched for live class but cannot select live class.");
			takescreenshot("User_live_class.png");
			System.out.println("Live class is not selected.");
		}
	}
	
	//Validate class
	public void check_redirection()
	{
		try {
			if(video.getText().contains(tag.getText()))
			{
				System.out.println("Correct video play");
				System.out.println("User choosen video is played.");
			}
		}
		catch(Exception e) {
			System.out.println("User choosen video is not played.");
		}
	}
}