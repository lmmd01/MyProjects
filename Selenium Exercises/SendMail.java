package exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMail {
	public void execute(WebDriver driver) {

		// Open webpage
		driver.get("https://outlook.live.com/owa/");
		// driver.manage().window().maximize();

		// Create a Object wait of WebdriverWait class
		WebDriverWait wait = new WebDriverWait(driver, 5);

		// Boolean - Final test result
		boolean result = false;

		// String - Message for email
		String msgMail = "";

		try {

			// --------Hotmail-------------
			// Buton Sign in
			driver.findElement(By.xpath("//a[@class='linkButtonSigninHeader']")).click();

			// Email
			driver.findElement(By.id("i0116")).clear();
			driver.findElement(By.id("i0116")).sendKeys("cuenta outlook");

			// Button next
			driver.findElement(By.id("idSIButton9")).click();

			// Password
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
			driver.findElement(By.id("i0118")).clear();
			driver.findElement(By.id("i0118")).sendKeys("pass outlook");

			// Button Submit
			driver.findElement(By.id("idSIButton9")).click();

			// Button New Message
			driver.findElement(By.id("id__5")).click();

			// Input Destinatario
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='ms-BasePicker-input pickerInput_55459320']")));
			driver.findElement(By.xpath("//input[@class='ms-BasePicker-input pickerInput_55459320']")).clear();
			driver.findElement(By.xpath("//input[@class='ms-BasePicker-input pickerInput_55459320']")).sendKeys("mike26pogo@gmail.com");

			// Subject
			driver.findElement(By.id("subjectLine0")).clear();
			msgMail = "Hola";
			driver.findElement(By.id("subjectLine0")).sendKeys(msgMail);

			// Message
			driver.findElement(By.xpath(
					"//div[@class='_4utP_vaqQ3UQZH0GEBVQe B1QSRkzQCtvCtutReyNZ _17ghdPL1NLKYjRvmoJgpoK _2s9KmFMlfdGElivl0o-GZb']")).clear();
			driver.findElement(By.xpath(
					"//div[@class='_4utP_vaqQ3UQZH0GEBVQe B1QSRkzQCtvCtutReyNZ _17ghdPL1NLKYjRvmoJgpoK _2s9KmFMlfdGElivl0o-GZb']")).sendKeys("Holaaa este es un mensaje de prueba!");

			// Button Send Message
			driver.findElement(By.id("id__972")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Error en thread sleep");
			}

			// Logout
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ms-Persona-initials initials-100']")));
			driver.findElement(By.xpath("//div[@class='ms-Persona-initials initials-100']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@class='ms-Button-label label-46'][text()='Sign out']")));
			driver.findElement(By.xpath("//DIV[@class='ms-Button-label label-46'][text()='Sign out']")).click();

			// ------ Gmail---

			// Open the webpage
			driver.get(
			"https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

			// Nombre de usuario
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//INPUT[@id='identifierId']")));
			driver.findElement(By.xpath("//INPUT[@id='identifierId']")).clear();
			driver.findElement(By.xpath("//INPUT[@id='identifierId']")).sendKeys("cuenta gmail");

			// Boton siguiente
			driver.findElement(By.xpath("//SPAN[@class='RveJvd snByac'][text()='Siguiente']")).click();
			
			// Validacion cuando no se ingresa un usuario correcto con Explicit wait
			try {
				// Using ExpectedConditions wait until element visibility
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@class='GQ8Pzc']")));

				if (driver.findElement(By.xpath("//DIV[@class='GQ8Pzc']")).isDisplayed()) {
					System.out.println("El nombre de usuario es incorrecto");
					result = false;
				}
			} catch (TimeoutException ex) {
				// No hay error en usuario

				// Contraseña
				driver.findElement(By.xpath("//INPUT[@type='password']")).clear();
				driver.findElement(By.xpath("//INPUT[@type='password']")).sendKeys("pass gmail");

				// Boton siguiente
				driver.findElement(By.xpath("//SPAN[@class='RveJvd snByac'][text()='Siguiente']")).click();

				try {
					// Using ExpectedConditions wait until element visibility
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@class='xgOPLd']")));

					if (driver.findElement(By.xpath("//DIV[@class='xgOPLd']")).isDisplayed()) {
						System.out.println("La contrasena es incorrecta");
						result = false;
					}
				} catch (TimeoutException ex2) {
					// No hay error en contrasena

					try {
						driver.findElement(By.xpath("//SPAN[@class='bog']/child::span[text()='" + msgMail + "']")).click();
						// Se completo el escenario
						result = true;
					} catch (NoSuchElementException eex) {
						System.out.println("No hay mensaje con esas coincidencias");
						result = false;
					} finally {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							System.out.println("Error en thread sleep");
						}
						// Dashboard de usuario
						driver.findElement(By.xpath("//SPAN[@class='gb_xa gbii']")).click();
						// Boton Salir
						driver.findElement(By.xpath("//A[@id='gb_71']")).click();
					}

				}
			}

		} catch (NoSuchElementException ex) {
			System.out.println("No se encotro un elemento");
			result = false;
		} finally {
			// Valor del resultado final del test
			System.out.println("\nEl resultado del test fue: " + result);
			// Salir del navegador
			 driver.quit();
		}

	}
}
