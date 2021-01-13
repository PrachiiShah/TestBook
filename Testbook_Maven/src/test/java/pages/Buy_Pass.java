/* Author: Prachi Shah
 * Date: 10-Jan-2020
 * Tests: Buy Pass Functionality
 * Description: Contains the methods for opting a purchase plan and 
 * 				choosing a payment method and making invalid payments.
 */

package pages;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import base.testBook_Base;

public class Buy_Pass extends testBook_Base{
	
	//Buy page elements.
	static @FindBy(id = "vpa-upi") WebElement upi_id;
	static @FindBy(id = "card_cvv") WebElement card_Cvv;
	static @FindBy(id ="tab-title") WebElement payment_back;
	static @FindBy(id = "card_number") WebElement card_number;
	static @FindBy(id = "fd-t") WebElement invalid_upi_payment;
	static @FindBy(id = "card_name") WebElement card_holder_name;
	static @FindBy(id = "card_expiry") WebElement card_expiry_date;
	static @FindBy(xpath = "//button[@method= 'upi']") WebElement upi_payment;
	static @FindBy(xpath = "//div[@id='modal-close']") WebElement close_button;
	static @FindBy(xpath = "//button[@method= 'card']") WebElement card_payment;
	static @FindBy(xpath = "//div[text()='Card']") WebElement invalid_card_payment;
	static @FindBy(xpath = "//a[@translate='BUY_PASS']") WebElement get_pass_button;
	static @FindBy(xpath = "//input[@value ='Weekly Testbook']") WebElement get_weekly;
	static @FindBy(xpath = "//input[@value ='Monthly Testbook']") WebElement get_monthly;
	static @FindBy(xpath = "//input[@value ='14 Month Testbook']") WebElement get_14month;
	static @FindBy(xpath = "//iframe[@class='razorpay-checkout-frame']") WebElement payment_frame;
	static @FindBy(xpath = "//div[@role='button']/span[contains(text(),'Pay')]") WebElement pay_button;
	static @FindBy(xpath = "//div[@data-block='rzp.cluster']/h3[@class ='title']")WebElement frame_title;
	static @FindBy(xpath = "//div[@class='pricing-list']/div/div[text()='Weekly Testbook ']") WebElement weekly_plan;
	static @FindBy(xpath = "//div[@class='pricing-list']/div/div[text()='Monthly Testbook ']") WebElement monthly_plan;
	static @FindBy(xpath = "//div[@class='pricing-list']/div/div[text()='14 Month Testbook ']") WebElement month14_plan;
	
	//Initialize excel
	FileInputStream fin;
	XSSFWorkbook wb;
	XSSFSheet ws;
	
	//Initialize asserts
	WebDriverWait wait = new WebDriverWait(driver, 10);
	SoftAssert softAssert = new SoftAssert();
	
	//Binds buy pass page elements.
	public Buy_Pass() {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Display plans
	public void plan_display(){
		System.out.println("The purchase plans displayed are: ");
		System.out.println("            1. "+weekly_plan.getText());
		System.out.println("            2. "+monthly_plan.getText());
		System.out.println("            3. "+month14_plan.getText());
	}
	
	//Choose a plan as data driven excel
	public void plan_choose() throws Exception{
		fin = new FileInputStream("src/test/resources/profile_update.xlsx");
		wb = new XSSFWorkbook(fin);
		ws = wb.getSheet("Plan");
		Row row;
		String plan;
		try{
			for(int r=1; r<=ws.getLastRowNum();r++) {
				row = ws.getRow(r);
				plan = row.getCell(0).getStringCellValue();
				if(plan.contains("Weekly")){
					get_weekly.click();
				}
				if(plan.contains("Monthly")){
					get_monthly.click();
				}
				if(plan.contains("14 Month")){
					get_14month.click();
				}
				log = ext.createTest("Choose a plan");
				log.log(Status.PASS, "User is able to choose his preferred plan.");
				takescreenshot(plan+"_Plan.png");
				System.out.println("User selected the plan: "+plan+" Plan");	
				Thread.sleep(1000);
			}
		}
		catch(Exception e){
			log = ext.createTest("Choose a plan");
			log.log(Status.FAIL, "User is not able to choose his preferred plan.");
			takescreenshot("Any_Plan.png");
			System.out.println("User plan not selected.");
		}
		wb.close();
		fin.close();
	}
	
	//Clicks on buy pass.
	public void click_buy_pass() throws Exception{
		wait.until(ExpectedConditions.visibilityOf(get_pass_button));
		get_pass_button.click();
		System.out.println("User clicks on Buy Pass");
	}
	
	//Validates payment methods displayed.
	public void validate_payment_method_displayed(){
		try{
			driver.switchTo().frame(payment_frame);
			Thread.sleep(2000);
			String title = frame_title.getText();
			softAssert.assertEquals(title, "Cards, UPI & More");
				log = ext.createTest("Payment_methods_displayed");
				log.log(Status.PASS,"User is able to view the payment plans.");
				takescreenshot("Payment_details.png");
				System.out.println("Payment frame is visible to user.");
				System.out.println("----------------------------------------------------------------------------");
		}
		catch(Exception e){
			log = ext.createTest("Payment_methods_displayed");
			log.log(Status.FAIL,"User is not able to view the payment plans.");
			takescreenshot("Payment_details.png");
			System.out.println("Payment frame not invoked.");
			System.out.println("--------------------------------------------------------------------------------");
		}
	}
	
	//Selects upi payment method
	public void select_upi_payment(){
		try{
			wait.until(ExpectedConditions.visibilityOf(upi_payment));
			upi_payment.click();
			Thread.sleep(1000);
			log = ext.createTest("Upi_payment");
			log.log(Status.PASS, "User has chosen upi payment method.");
			takescreenshot("Upi_Payment_Opted.png");
			System.out.println("User opted for UPI Payment method.");
		}
		catch(Exception e){
			log = ext.createTest("Upi_payment");
			log.log(Status.FAIL, "User has not chosen upi payment method.");
			takescreenshot("Upi_Payment_Opted.png");
			System.out.println("User opted for other payment type.");
		}
	}
	
	//Selects card payment method
	public void select_card_payment() {
		try {
			wait.until(ExpectedConditions.visibilityOf(card_payment));
			card_payment.click();
			Thread.sleep(1000);
			log = ext.createTest("Card_payment");
			log.log(Status.PASS, "User has chosen card payment method.");
			takescreenshot("Card_Payment_Opted.png");
			System.out.println("User opted for Card Payment method.");
		}
		catch(Exception e) {
			log = ext.createTest("Card_payment");
			log.log(Status.FAIL, "User has not chosen card payment method.");
			takescreenshot("Card_Payment_Opted.png");
			System.out.println("User opted for other payment type.");
		}
	}
	
	//Adds upi id as data driven from excel
	public void add_upi_id() throws Exception{
		fin = new FileInputStream("src/test/resources/profile_update.xlsx");
		wb = new XSSFWorkbook(fin);
		ws = wb.getSheet("UPI");
		Row row;
		try{
			wait.until(ExpectedConditions.visibilityOf(upi_id));
			upi_id.click();
			String id;
			for(int r=1;r<=ws.getLastRowNum();r++){
				row = ws.getRow(r);
				id = row.getCell(0).getStringCellValue();
				if(id.contains("@")) {
					upi_id.clear();
					upi_id.sendKeys(id);
					Thread.sleep(1000);
					log = ext.createTest("Upi_valid_input");
					log.log(Status.PASS,"User has entered the valid upi id.");
					takescreenshot("Valid_Upi_input.png");
					wait.until(ExpectedConditions.visibilityOf(pay_button));
					pay_button.click();
					System.out.println("User has entered his valid upi id.");
				}
				else{
					upi_id.clear();
					upi_id.sendKeys(id);
					Thread.sleep(1000);
					log = ext.createTest("Upi_input");
					log.log(Status.FAIL,"User has entered the upi id.");
					takescreenshot("Invalid_Upi_input.png");
					wait.until(ExpectedConditions.visibilityOf(pay_button));
					pay_button.click();
					System.out.println("User has entered invalid upi id.");
					Thread.sleep(3000);
					payment_back.click();
					select_upi_payment();
				}
			}	
		}
		catch(Exception e){
			log = ext.createTest("Upi_input");
			log.log(Status.FAIL,"User has not entered the upi id.");
			takescreenshot("Upi_id_input.png");
			System.out.println("User has not entered his valid upi id.");
		}
		wb.close();
		fin.close();
	}
	
	//Adds card details as data driven from excel
	public void add_card_details() throws Exception{
		fin = new FileInputStream("src/test/resources/profile_update.xlsx");
		wb = new XSSFWorkbook(fin);
		ws = wb.getSheet("Card");
		Row row;
		try{
			wait.until(ExpectedConditions.visibilityOf(card_number));
			for(int r=1;r<=ws.getLastRowNum();r++){
				row = ws.getRow(r);
				String number = row.getCell(0).getStringCellValue();
				String exp_date = row.getCell(1).getStringCellValue();
				String name = row.getCell(2).getStringCellValue();
				String cvv= row.getCell(3).getStringCellValue();
				if(number.isEmpty() == true){
					card_number.click();
					card_number.sendKeys(number);
					Thread.sleep(1000);
					card_expiry_date.click();
					card_expiry_date.sendKeys(exp_date);
					Thread.sleep(1000);
					card_holder_name.click();
					card_holder_name.sendKeys(name);
					Thread.sleep(1000);
					card_Cvv.click();
					card_Cvv.sendKeys(cvv);
					Thread.sleep(1000);
					log = ext.createTest("Card_details_input");
					log.log(Status.FAIL,"User has entered invalid card details.");
					takescreenshot("Invalid_card_details.png");
					pay_button.click();
					System.out.println("User has entered the invalid card details.");
					Thread.sleep(1000);
					payment_back.click();
					select_card_payment();
				}
				else{
					card_expiry_date.click();
					card_expiry_date.sendKeys(exp_date);
					Thread.sleep(1000);
					card_holder_name.click();
					card_holder_name.sendKeys(name);
					Thread.sleep(1000);
					card_Cvv.click();
					card_Cvv.sendKeys(cvv);
					Thread.sleep(1000);
					card_number.click();
					card_number.sendKeys(number);
					Thread.sleep(1000);
					log = ext.createTest("Card_details_input");
					log.log(Status.PASS,"User has entered the valid card details.");
					takescreenshot("Valid_card_details.png");
					pay_button.click();
					System.out.println("User has entered his valid card details.");
				}
			}			
		}
		catch(Exception e){
			log = ext.createTest("Card_input");
			log.log(Status.FAIL,"User has not entered the card details.");
			takescreenshot("Card_details_input.png");
			System.out.println("User has not entered his card details.");
		}
		wb.close();
		fin.close();
	}
		
	//Validates_upi_payment
	public void validate_upi_payment_status(){
		try{
			System.out.println("User clicks on Pay button.");
			Thread.sleep(1000);
			String error_mes = invalid_upi_payment.getText();
			softAssert.assertEquals(error_mes, "Invalid VPA.");
				log = ext.createTest("Payment_validation");
				log.log(Status.PASS,"User is able to view the invalid payment details message.");
				takescreenshot("Invalid_UPI_Payment_Error.png");
				System.out.println("User is displayed the invalid VPA error.");
				System.out.println("--------------------------------------------------------------------------------");
				close_button.click();
		}
		catch(Exception e){
			log = ext.createTest("Payment_validation");
			log.log(Status.FAIL,"User is not able to view the invalid payment details message.");
			takescreenshot("Invalid_UPI_Payment_Error.png");
		}
	}
	
	//Validates_card_payment
	public void validate_card_payment_status(){
		try{
			wait.until(ExpectedConditions.visibilityOf(pay_button));
			pay_button.click();
			System.out.println("User clicks on Pay button.");
			Thread.sleep(1000);
			String error_mes = invalid_card_payment.getText();
			softAssert.assertEquals(error_mes, "Card");
				log = ext.createTest("Payment_validation");
				log.log(Status.PASS,"User is able still on the card details page.");
				takescreenshot("Invalid_Card_Payment_Error.png");
				System.out.println("User is still on the card details page. Validation failed.");
				System.out.println("--------------------------------------------------------------------------------");
				close_button.click();
		}
		catch(Exception e){
			log = ext.createTest("Payment_validation");
			log.log(Status.FAIL,"User is not able to view the invalid payment details message.");
			takescreenshot("Invalid_Card_Payment_Error.png");
			System.out.println("User is still on the card details page. Validation failed.");
			System.out.println("--------------------------------------------------------------------------------");
		}
	}
}
