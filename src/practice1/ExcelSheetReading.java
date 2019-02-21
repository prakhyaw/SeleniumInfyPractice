package practice1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelSheetReading {

	
	String filepath = "C://Users//nagaprakhya_w//Desktop//IVSIS_Cred_26May17_1802.xlsx";
	
	@Test
	public void test() throws IOException {
		
		System.out.println(filepath);
		//File filepath = new File("C://Users//nagaprakhya_w//Desktop//IVSIS_Cred_26May17_1802.xlsx");
		FileInputStream file = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Credentials");
		
		int rows = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println("No of rows :"+rows);
		
		System.out.println("Before Adding");
		for(int i = 1; i<=rows; i++)
		{
			System.out.println("Username: "+sheet.getRow(i).getCell(0).getStringCellValue());
			System.out.println("Password: "+sheet.getRow(i).getCell(1).getStringCellValue());
		}
		
		sheet.getRow(0).createCell(3).setCellValue("WelcomeMessage");		
		for(int i = 1; i<=rows; i++)
		{
			sheet.getRow(i).createCell(3).setCellValue("Hi "+sheet.getRow(i).getCell(0).getStringCellValue());
		}
		
		System.out.println("After Adding");
		for(int i = 1; i<=rows; i++)
		{
			System.out.println("Username: "+sheet.getRow(i).getCell(0).getStringCellValue());
			System.out.println("Password: "+sheet.getRow(i).getCell(1).getStringCellValue());
			System.out.println("Message: "+sheet.getRow(i).getCell(3).getStringCellValue());
		}
		
		FileOutputStream outputfile = new FileOutputStream(filepath);
		workbook.write(outputfile);
		System.out.println("Updated workbook");
		workbook.close();		
	}

}
