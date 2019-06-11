package packageTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSelenium {
    
	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	String profile;

	@BeforeTest
	public void initialization() {

		// To set the path of the Chrome driver.
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\duarte\\Desktop\\TATA\\Eclipse\\ZIP\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void MyExercise() throws IOException {
		// Import excel sheet.
		File src = new File("C:\\Users\\duarte\\Desktop\\TATA\\Eclipse\\test.xlsx");
		// Load the file.
		FileInputStream fis = new FileInputStream(src);
		// Load he workbook.
		workbook = new XSSFWorkbook(fis);
		// Load the sheet in which data is stored.
		sheet = workbook.getSheetAt(0);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			// Import data for Profile.
			cell = sheet.getRow(i).getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);

			profile = cell.getStringCellValue();

			// To launch website
			driver.get("https://www.phptravels.net/" + cell.getStringCellValue());
			// To maximize the browser
			driver.manage().window().maximize();
			// Implicit wait
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			// Import data for Email.
			cell = sheet.getRow(i).getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//input[@type='text'][@name='email']")).clear();
			driver.findElement(By.xpath("//input[@type='text'][@name='email']")).sendKeys(cell.getStringCellValue());

			// Import data for password.
			cell = sheet.getRow(i).getCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//input[@type='password'][@name='password']")).clear();
			driver.findElement(By.xpath("//input[@type='password'][@name='password']"))
					.sendKeys(cell.getStringCellValue());

			// To click on Login button
			driver.findElement(By.cssSelector("button.ladda-button")).click();

			// Implicit wait
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//To write data in the excel 
			 FileOutputStream fos=new FileOutputStream(src);
			 //Message to be written in the excel sheet 
			 String message;; //
			

			if (driver.findElement(By.id("account")).isDisplayed()) {
			    
				// To click on Hotels dropdown
				driver.findElement(By.cssSelector("a[href*='#Hotels']")).click();
				// To click on Hotels sub-dropdown
				driver.findElement(By.cssSelector("a[href*='hotels']")).click();

                /*
                --ADMIN
				driver.findElement(By.cssSelector("a[href*='https://www.phptravels.net/" +
				profile + "-portal/" + profile + "/hotels']")).click();
                --SUPPLIER
				driver.findElement(By.cssSelector("a[href*='https://www.phptravels.net/" +
				profile + "/hotels']")).click();
                */

				// To click on Add button
				driver.findElement(By.cssSelector("form.add_button")).click();
				// Implicit wait
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				// Import data for Hotel Name.
				cell = sheet.getRow(i).getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//input[@type='text'][@name='hotelname']")).clear();
				driver.findElement(By.xpath("//input[@type='text'][@name='hotelname']"))
						.sendKeys(cell.getStringCellValue());
						
				// Import data for Hotel Description.
				cell = sheet.getRow(i).getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				
				// Implicit wait for loading Description
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				/*
				 * driver.findElement(By.cssSelector("body.cke_editable")).click();
				 * driver.findElement(By.cssSelector("body.cke_editable")).clear();
				 * driver.findElement(By.cssSelector("body.cke_editable")).sendKeys(cell.
				 * getStringCellValue());
				 */

				// Import data for Hotel Location.
				cell = sheet.getRow(i).getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.className("select2-chosen")).click();
				driver.findElement(By.className("select2-input")).clear();
				driver.findElement(By.className("select2-input")).sendKeys(cell.getStringCellValue());
				driver.findElement(By.className("select2-result-label")).click();

				// To click on Logout button
				driver.findElement(By.id("logout")).click();
				
				//Test result
				message = "PASSED";

			} else {
				System.out.println("Hay error.");
				//Test result
				message = "FAILED";

			}
			 
			 	//Create cell where data needs to be written.
				sheet.getRow(i).createCell(8).setCellValue(message); 
				// finally write content
				workbook.write(fos);
				
				// Implicit wait for loading Description
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				// close the file
                fos.close();
			 

		}
	}

}

