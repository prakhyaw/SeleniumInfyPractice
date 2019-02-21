package practice1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Case5 {
	
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
		
		driver.findElement(By.linkText("Add Payee")).click();
		
		try {
		
		String filepath = "C:\\Users\\nagaprakhya_w\\Desktop\\IVSIS_Exercise04_TestData_26May17_1830.xlsx";
		FileInputStream file = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Test Data");
		
		int rows = sheet.getLastRowNum() - sheet.getFirstRowNum();
		DataFormatter formatter = new DataFormatter();
		for(int i = 1; i<=rows; i++)
		{
			driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtPayeeAccountNumber")).
				sendKeys(formatter.formatCellValue(sheet.getRow(i).getCell(0)));
			driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtReEnterAccountNumber")).
				sendKeys(formatter.formatCellValue(sheet.getRow(i).getCell(1)));
			driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtPayeeName")).
				sendKeys(formatter.formatCellValue(sheet.getRow(i).getCell(2)));
			driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtPayeeNickName")).
				sendKeys(formatter.formatCellValue(sheet.getRow(i).getCell(3)));
			
			driver.findElement(By.name("ctl00$ctl00$body$cph_MyAccount$btnAddPayee")).click();
			Thread.sleep(1000);
			
			driver.switchTo().alert().accept();
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("C:\\Users\\nagaprakhya_w\\Desktop\\screenshot_"+i+".png"));
			
			driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtPayeeAccountNumber")).clear();
			driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtReEnterAccountNumber")).clear();
			driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtPayeeName")).clear();
			driver.findElement(By.id("ctl00_ctl00_body_cph_MyAccount_txtPayeeNickName")).clear();
		}
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		driver.findElement(By.linkText("Log Out")).click();
		driver.close();
	}
}
