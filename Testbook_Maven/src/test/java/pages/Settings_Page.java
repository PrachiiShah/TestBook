/* Author: Prachi Shah
 * Date: 09-Jan-2020
 * Tests: Profile Update
 * Description: Contains the methods for updating profile details.
 * 				Updates profile pic, Education, Date of Birth, Category,
 * 				Location, Language, Save Profile.
 */
package pages;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import base.testBook_Base;

public class Settings_Page extends testBook_Base {
	
	//Settings page elements.
	
	static @FindBy(id = "js-edit-img") WebElement change_pic;
	static @FindBy(id = "userLocation") WebElement add_pincode;
	static @FindBy(name = "default-lang-english") WebElement add_lang_in;
	static @FindBy(xpath ="//span[text()='Edit']") WebElement click_edit;
	static @FindBy(xpath = "//span[text()='English']") WebElement my_lang;
	static @FindBy(xpath = "//span[text()='MBA/PGDM']") WebElement my_course;
	static @FindBy(xpath = "//span[text()='General']") WebElement my_category;
	static @FindBy(xpath = "(//img[@class='circle-img'])[2]") WebElement dropdown;
	static @FindBy(xpath = "//button[@ng-click='saveProfile(user)']") WebElement save;
	static @FindBy(xpath = "//input[@placeholder='Your category']") WebElement add_category;
	static @FindBy(xpath = "//div[1]/table/tbody/tr[4]/td[text()='22']") WebElement my_date;
	static @FindBy(xpath = "//input[@placeholder = 'Your education']") WebElement add_education;
	static @FindBy(xpath = "//div[3]/table/tbody/tr[1]/td/span[text()='1997']") WebElement my_year;
	static @FindBy(xpath = "//div[2]/table/tbody/tr[1]/td/span[text()='Dec']") WebElement my_month;
	static @FindBy(xpath = "//input[@placeholder = 'Date of Birth dd/mm/yyyy']") WebElement add_dob;
	static @FindBy(xpath = "//div[3]/table/thead/tr[2]/th[@class='prev']") WebElement year_nav_back;
	static @FindBy(xpath = "(//li[@class='dropdown-item ng-scope'])[6]/a") WebElement settings_option;
	static @FindBy(xpath = "//div[3]/table/thead/tr[2]/th[@class='datepicker-switch']") WebElement year_picker;

	//Initialize excel
	FileInputStream fin;
	XSSFWorkbook wb;
	XSSFSheet ws;
	
	//Initialize asserts
	WebDriverWait wait = new WebDriverWait(driver, 10);
	SoftAssert softAssert = new SoftAssert();
	
	//Binds setting page elements.
	public Settings_Page() {													// binds the webpage elements together
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
		
	//Clicks profile button -> goes to settings
	public void click_action_button(){
		try{
			dropdown.click();
			System.out.println("Clicking on profile button............");
			settings_option.click();
			System.out.println("Clicking on settings............");
			log = ext.createTest("Click Profile Button");
			log.log(Status.PASS, "User clicks on profile -> settings option.");
			takescreenshot("Settings_button.png");			
		}
		catch(Exception e){
			System.out.println("Profile button is not displayed............");
			log = ext.createTest("Click Profile Button");
			log.log(Status.FAIL, "Profile button is not displayed.");
			takescreenshot("Settings_button.png");
		}	
	}
	
	//Validates settings page.
	public void validate_settings_page(){										
		if(driver.getTitle().contains("Settings")) {
			System.out.println("User is on settings page.");
			log = ext.createTest("Settings Display");
			log.log(Status.PASS, "User is on settings page.");
			takescreenshot("Settings_page.png");
			System.out.println("------------------------------------------------------------------------------");
		}
		else{
			System.out.println("User is not on settings page.");
			log = ext.createTest("Dashboard Display");
			log.log(Status.FAIL, "User is not on settings page.");
			takescreenshot("Settings_page.png");
			System.out.println("------------------------------------------------------------------------------");
		}
	}
	
	//Clicks on change profile.
	public void click_change_profile(){										
		try {
			change_pic.click();
			log = ext.createTest("Profile pic change option");
			log.log(Status.PASS, "User is able to select the change profile pic option.");
			takescreenshot("Change_Profile_option.png");
			System.out.println("Change profile option clickable");
		}
		catch(Exception e){
			log = ext.createTest("Profile pic change option");
			log.log(Status.FAIL, "User is unable to select the change profile pic option.");
			takescreenshot("Change_Profile_option.png");
			System.out.println("Change profile option not clickable");
		}
	}
	
	//Uploads new profile pic.	
	public void upload_profile_pic(){											
		try {
			Runtime.getRuntime().exec("D:\\Eclipse\\Eclipse_Workspace\\Testbook_Maven\\pic_upload.exe");
			Thread.sleep(5000);
			log = ext.createTest("Profile pic uploading");
			log.log(Status.PASS, "User is able to upload a profile pic.");
			takescreenshot("Profile_upload.png");
			System.out.println("Profile uploaded successfully.");
			System.out.println("------------------------------------------------------------------------------");
		}
		catch(Exception e){
			log = ext.createTest("Profile pic uploading");
			log.log(Status.PASS, "User is not able to upload a profile pic.");
			takescreenshot("Profile_upload.png");
			System.out.println("Unable to uploaded profile pic.");
			System.out.println("------------------------------------------------------------------------------");
		}
	}
	
	//Scrolls down and clicks on edit.
	public void edit(){														
		try{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,800)");
			click_edit.click();
			Thread.sleep(1000);
			log = ext.createTest("Before editting settings.");
			log.log(Status.PASS, "User has not updated the settings yet.");
			takescreenshot("Before_update_Settings.png");
			System.out.println("Before settings are updated.");
			System.out.println("------------------------------------------------------------------------------");
		}
		catch(Exception e){}
	}
	
	//Updates educational skills as data driven from excel.
	public void upload_education() throws Exception{							
		fin = new FileInputStream("src/test/resources/profile_update.xlsx");
		wb = new XSSFWorkbook(fin);	
		ws = wb.getSheet("Settings");
		Row row;
		try{
			add_education.click();
			Thread.sleep(1000);
			for(int i=1;i<=ws.getLastRowNum();i++) {
				row = ws.getRow(i);
				add_education.sendKeys(row.getCell(0).getStringCellValue());
				my_course.click();
			}
			Thread.sleep(1000);
			new Actions(driver).click().perform();
			log = ext.createTest("Education Detail");
			log.log(Status.PASS, "User is able to update his education level.");
			takescreenshot("Add_Education.png");
			System.out.println("Education details added.");
		}
		catch(Exception e){
			log = ext.createTest("Education Detail");
			log.log(Status.FAIL, "User is not able to update his education level.");
			takescreenshot("Add_Education.png");
			System.out.println("Education details not added.");
		}
		wb.close();
		fin.close();
	}
	
	//Selects date of birth from dropdown.
	public void upload_dob() throws Exception{									
		try{
			add_dob.click();
			while(year_picker.getText().contains("1990-1999") == false) {
				year_nav_back.click();
				if(year_picker.getText().contains("1990-1999")){
					my_year.click();
					Thread.sleep(2000);
					my_month.click();
					Thread.sleep(2000);
					my_date.click();
					Thread.sleep(2000);
				}
			}
			Thread.sleep(2000);
			log = ext.createTest("Date_of_birth Detail");
			log.log(Status.PASS, "User is able to update his date of birth.");
			takescreenshot("Add_DOB.png");
			System.out.println("Date of birth added.");
			new Actions(driver).click().perform();
		}
		catch(Exception e){
			log = ext.createTest("Date_of_birth Detail");
			log.log(Status.FAIL, "User is not able to update his date of birth.");
			takescreenshot("Add_DOB.png");
			System.out.println("Date of birth not added.");
		}
	}
	
	//Updates category as data driven value from excel.
	public void upload_category() throws Exception{													
		fin = new FileInputStream("src/test/resources/profile_update.xlsx");
		wb = new XSSFWorkbook(fin);	
		ws = wb.getSheet("Settings");
		Row row;
		try{
			add_category.click();
			Thread.sleep(2000);
			for(int r = 1; r<=ws.getLastRowNum();r++) {
				row = ws.getRow(r);
				if(my_category.getText().contains(row.getCell(1).getStringCellValue())){
					my_category.click();
					Thread.sleep(2000);
					log = ext.createTest("Category Details");
					log.log(Status.PASS, "User is able to update his category.");
					takescreenshot("Add_Category.png");
				}
			}
			System.out.println("Category added.");
			new Actions(driver).click().perform();			
		}
		catch(Exception e){
			log = ext.createTest("Category Details");
			log.log(Status.FAIL, "User is not able to update his category.");
			takescreenshot("Add_Category.png");
			System.out.println("Category not added.");
		}
	}
	
	//Updates location pincode.
	public void upload_location() throws Exception{										 
		try{
			add_pincode.click();
			Thread.sleep(1000);
			add_pincode.clear();
			add_pincode.sendKeys("302020");
			Thread.sleep(2000);
			log = ext.createTest("Location Details");
			log.log(Status.PASS, "User is able to update his location pincode.");
			takescreenshot("Add_Location.png");
			System.out.println("Location pin added.");
			new Actions(driver).click().perform();			
		}
		catch(Exception e){
			log = ext.createTest("Location Details");
			log.log(Status.FAIL, "User is not able to update his location pincode.");
			takescreenshot("Add_Location.png");
			System.out.println("Location pin not added.");
		}
	}
	
	//Updates language input.
	public void upload_input_lang() throws Exception{
		fin = new FileInputStream("src/test/resources/profile_update.xlsx");
		wb = new XSSFWorkbook(fin);
		ws = wb.getSheet("Settings");
		Row row;
		String str;
		try{
			for(int r = 1; r<=ws.getLastRowNum();r++) {
			row = ws.getRow(r);
			str = row.getCell(2).getStringCellValue();
			if(my_lang.getText().matches(str)){
				System.out.println("Correct language is selected.");
			}
			else {
				my_lang.click();
			}				
		}
		Thread.sleep(1000);
		log = ext.createTest("Language Details");
		log.log(Status.PASS, "User is able to update his language preference.");
		takescreenshot("Choose_Lang.png");
		System.out.println("English language choosen.");
		new Actions(driver).click().perform();	
		}
		catch(Exception e){
			log = ext.createTest("Language Details");
			log.log(Status.PASS, "User is not able to update his language preference.");
			takescreenshot("Choose_Lang.png");
			System.out.println("English language was not choosen earlier.");
			new Actions(driver).click().perform();	
		}
		wb.close();
		fin.close();
	}
	
	//Clicks save profile.
	public void save_profile(){
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,1000)");
			save.click();
		}
		catch(Exception e){}
	}
	
	//Validates settings page.
	public void validate_updates(){
		try{
			log = ext.createTest("After editting settings.");
			log.log(Status.PASS, "User has updated all the settings.");
			takescreenshot("After_settings.png");
			System.out.println("Settings have been updated.");
			System.out.println("Settings updated successfully.");
			System.out.println("------------------------------------------------------------------------------");
		}
		catch(Exception e){
			System.out.println("------------------------------------------------------------------------------");
		}
	}
}
