package practice1;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Case2 {

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
		
		driver.findElement(By.linkText("Deposit")).click();
		driver.findElement(By.linkText("Close FD/RD Account")).click();
		driver.findElement(By.id("ctl00_ctl00_body_cph_Deposit_rbtnDepositType_1")).click();
		
		WebElement deposits = driver.findElement(By.id("ctl00_ctl00_body_cph_Deposit_ddlMyDeposits"));
		Select select = new Select(deposits);
		select.selectByIndex(3);
		
		driver.findElement(By.id("ctl00_ctl00_body_cph_Deposit_chkNewTransfer")).click();
		
	}

}
