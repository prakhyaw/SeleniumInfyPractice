package practice1;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class Case1 {	
	
	WebDriver driver;
	
	
	@Before
	public void login() {
		
		System.setProperty("webdriver.chrome.driver","D:\\Softwares\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://10.67.89.42/Common/Login.aspx");		
	}
	
	@Test
	public void test() throws InterruptedException {
		driver.findElement(By.id("ctl00_body_txtUserID")).sendKeys("donhere");
		driver.findElement(By.id("ctl00_body_txtPassword")).sendKeys("don@123");
		driver.findElement(By.id("ctl00_body_btnLogin")).click();
		Thread.sleep(2000);
		
		System.out.println("Title of the page is :"+driver.getTitle());
		
		driver.findElement(By.linkText("Change User ID")).click();
		
		driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtNewUserId")).sendKeys("JohnDoe");
		driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtConfirmNewUserId")).sendKeys("JohnDoe");
		driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtSecurityAnswer")).sendKeys("Bhairav");
		driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_btnUpdate")).click();
		
		String securityStatus = driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_lblStatus")).getText();
		Assert.assertEquals("Invalid security answer.", securityStatus);
	}
	
	@After
	public void logout() {
		driver.close();
	}
	
	

}
