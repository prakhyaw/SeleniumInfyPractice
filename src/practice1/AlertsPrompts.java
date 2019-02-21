package practice1;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsPrompts {

	WebDriver driver;	
	
	@Before
	public void login() {
		
		System.setProperty("webdriver.chrome.driver","D:\\Softwares\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://www.google.co.in");
	}
	
	@Test
	public void test() throws IOException {
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("C:\\Users\\nagaprakhya_w\\Desktop\\screenshot.png"));
		/*driver.findElement(By.xpath("//input[@value='Alert']")).click();
		driver.switchTo().alert().accept();
		
		driver.findElement(By.xpath("//input[@value='Confirmation']")).click();
		driver.switchTo().alert().accept();
		
		driver.findElement(By.xpath("//input[@value='Confirmation']")).click();
		driver.switchTo().alert().dismiss();
		
		driver.findElement(By.xpath("//input[@value='Prompt']")).click();
		driver.switchTo().alert().sendKeys("Prakhya");
		driver.switchTo().alert().accept();	*/	
	}
	
	@After
	public void tearDown() {
		driver.close();
	}

}
