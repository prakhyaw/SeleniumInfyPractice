package practice1;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Case4 {
	
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
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("View Transactions")).click();
		Select accountNo = new Select(driver.findElement(By.name("ctl00$ctl00$body$cph_MyAccount$ddlAccountNo")));
		accountNo.selectByVisibleText("100000000011");
		
		driver.findElement(By.xpath("//input[@id='ctl00_ctl00_body_cph_MyAccount_rblTransactions_1']")).click();
		
		Select transactionType = new Select(driver.findElement(By.name("ctl00$ctl00$body$cph_MyAccount$ddlTransactionType")));
		transactionType.selectByIndex(9);
		
		driver.findElement(By.name("ctl00$ctl00$body$cph_MyAccount$txtFromDate")).sendKeys("1/1/2013");
		driver.findElement(By.name("ctl00$ctl00$body$cph_MyAccount$txtToDate")).sendKeys("1/1/2017");
		driver.findElement(By.name("ctl00$ctl00$body$cph_MyAccount$btnViewTrancastions")).click();
		String statusMessage = driver.findElement(By.className("ajax__validatorcallout_error_message_cell")).getText();
		System.out.println(statusMessage);		
	}
}
