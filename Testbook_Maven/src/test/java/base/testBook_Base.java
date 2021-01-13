/* Author: Prachi Shah
 * Date: 08-Jan-2020
 * Tests: Initialize the browser, extent report, screenshots and kill the browser.exe file. 
 * Description: Contains the methods to be exexuted before and after running the workflow.
 * 				Includes: browser initialization, setup the basic extent report format,
 * 				initializes screenshot method and flush all report data and kill the browser.
 */

package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class testBook_Base {
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentHtmlReporter htmlreport;
	public static ExtentReports ext;
	public static ExtentTest log;
	
	//Cucumber annotation - runs before executing anything in work flow.														
	@Before("@Testbook_Page")																			
	public void before_scenario()
	{		
		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/test/resources/config_testbook.properties"));
		}
		catch (Exception e) {}
		
		String browser=prop.getProperty("browser");
		if(browser.matches("firefox"))		{
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		}
		if(browser.matches("chrome")){
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		}	
		
		htmlreport = new ExtentHtmlReporter(prop.getProperty("reportloc")+"\\Testbook.html");
		ext = new ExtentReports();
		ext.attachReporter(htmlreport);
		ext.setSystemInfo("Host Name", "My System");
		ext.setSystemInfo("Environment", "Test Env");
		ext.setSystemInfo("User Name", "Prachi Shah");										 
		   
		htmlreport.config().setDocumentTitle("TestBook Report");										
		htmlreport.config().setReportName("TestBook Functional Testing");
		htmlreport.config().setTestViewChartLocation(ChartLocation.TOP);					
		htmlreport.config().setTheme(Theme.STANDARD);	
		   
		System.out.println("Before TestBook Functional Testing");
	}
	
	//Definition for screenshot method - saves screenshot in local system and adds it to extent report as well.
	public void takescreenshot(String imagename){
		File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);				
		try{
			FileUtils.copyFile(f, new File(prop.getProperty("screenshot")+"\\"+imagename));	
			log.addScreenCaptureFromPath(prop.getProperty("screenshot")+"\\"+imagename);	
		}
		catch(Exception e) {}
	}
	
	@After("@Payment_methods_card")
//	@After("@Payment_methods_card")																								
	public void after_scenario()	{
		ext.flush();
		driver.quit();
		try		{
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
		}
		catch(Exception e) {}
		System.out.println("After TestBook Functional Testing");
	}

}
