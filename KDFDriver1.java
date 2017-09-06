//Keyword Driven Testing( Keywords like- clickLink, clickElement, verifyText, openBrowser, closeBrowser, navigateBrowser, typeEdit) 


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class KDFDriver1 {
WebDriver myD;
		
	@Test 
	public void myTest() {
	 
	//Purpose: Will click on the link using the link text
	//I/O:  LinkText
	//O/P:
	//Created on/By: Jayashri 23rd Aug 17
	//Modified by/on: Jayashri  26rd Aug 17
	//UM_TC002- Login- Test Negative scenario
	//1	Open Browser	openBrowser			Chrome
	//2	Navigate	navigateBrowser			http://dev.atomic77.in/ANATv1
	//3	Click Login 	ClickLink	LinkText	Login
	//4	Type Username	typeText	xPath	.//*[@id='user_login']	Jaya
	//5	Type Password	typeText	xPath	.//*[@id='user_pass']	Jaya123
	//6 Click on Sign In button
	//7	Verify Err Msg	verifyText	xPath	.//*[@id='alert-error']	Login authentication failed
	//8	Close Browser	closeBrowser
		
	//Reusable functions- Using following keywords created functions
	  openBrowser("Firefox");
	  navigateBrowser("http://dev.atomic77.in/ANATv1/");
	  clickLink("Login");
	  typeText("//*[@id='user_login']", "Jayashri_patil2@yahoo.com");
	  typeText(".//*[@id='user_pass']","Jaya123");
	  clickElement("//*[@id='singlebutton']");
	  System.out.println(verifyText("//div[@id='alert-error']","  Login authentication failed"));
      closeBrowser();
}
	
	public void openBrowser(String fTD) {
		if (fTD.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Jayashri\\workspace\\CSDT Drivers\\geckodriver.exe");
			myD = new FirefoxDriver();
		} 
		if(fTD.equals("Chrome")) {
			System.setProperty("webdriver.Chrome.driver", "C:\\Users\\Jayashri\\workspace\\CSDT Drivers\\chromedriver.exe");
			myD = new ChromeDriver();
		}
		if(fTD.equals("IE")) {
			System.setProperty("webdriver.internetexplorer.driver", "C:\\Users\\Jayashri\\workspace\\CSDT Drivers\\internetexplorerdriver.exe");
			myD = new InternetExplorerDriver();
		}	
		myD.manage().window().maximize();
		}
	public void navigateBrowser(String fTD) {
		myD.get(fTD);
		}
	public void clickLink(String fEID) {
	    myD.findElement(By.linkText(fEID)).click();
		}
	public void typeText(String fEID, String fTD)  {
		myD.findElement(By.xpath(fEID)).clear();
		myD.findElement(By.xpath(fEID)).sendKeys(fTD);
		}
	public void clickElement(String fEID) {
			myD.findElement (By.xpath(fEID)).click();
		}
   	public String verifyText(String fEID, String fTD) {
	String fText = myD.findElement (By.xpath(fEID)).getText(); 
	System.out.println("Exp Text : "+ fTD);
	System.out.println("Act Text : "+ fText);	
	if (fText.equals(fTD)) {
	return "Pass";
	} else {
	return "Fail";
	}
	}
		
	public void closeBrowser()  {
	myD.quit();
	}
}
