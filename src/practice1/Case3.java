package practice1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Case3 {
	
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
		
		WebElement table = driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_gvAccountDetails"));
		List<WebElement> columns = driver.findElements(By.xpath("//table[@id='ctl00_ctl00_body_cph_MyAccount_gvAccountDetails']//tr[2]//td"));
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='ctl00_ctl00_body_cph_MyAccount_gvAccountDetails']//tr"));
		
		System.out.println(rows.size());
		System.out.println(columns.size());
		
		//printing all the rows and columns
		 for(int i = 2; i <rows.size() -1; i++)
		{			
			for(int j = 1; j <= columns.size(); j++)
				{
					System.out.println(driver.findElement(By.xpath("//tr//th["+j+"]")).getText()+" :" + 
				driver.findElement(By.xpath("//table[@id='ctl00_ctl00_body_cph_MyAccount_gvAccountDetails']//tr["+i+"]//td["+j+"]")).getText());	
				}
				
		}
		
		//printing particular rows
		for(int i = 2; i <rows.size() -1; i++)
		{			
			for(int j = 1; j <= columns.size(); j++)
				{
					String accountType = driver.findElement(By.xpath("//table[@id='ctl00_ctl00_body_cph_MyAccount_gvAccountDetails']//tr["+i+"]//td[4]")).getText();
					if(accountType.equals("FD"))
					{
						System.out.println(driver.findElement(By.xpath("//tr//th["+j+"]")).getText()+" :" +
						driver.findElement(By.xpath("//table[@id='ctl00_ctl00_body_cph_MyAccount_gvAccountDetails']//tr["+i+"]//td["+j+"]")).getText());
					}						
				}				
		}
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
}
