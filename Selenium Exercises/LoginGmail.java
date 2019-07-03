package exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginGmail {
	public void execute(WebDriver driver) {

		// Open the webpage
		driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

		// Maximizar el navegador
		// driver.manage().window().maximize();

		// Create a Object wait of WebdriverWait class
		WebDriverWait wait = new WebDriverWait(driver, 5);

		// Valor del resultado final del test
		boolean result = false;

		// Constante de mensaje de error
		final String MSG_ERROR_USER = "No se ha podido encontrar tu cuenta de Google";

		// Constante de mensaje de error
		final String MSG_ERROR_PASS = "Contraseña incorrecta. Vuelve a intentarlo o haz clic en Contraseña olvidada para cambiarla.";

		// Mensaje obtenido de la webpage
		String msgError = "";

		try {

			// Nombre de usuario
			driver.findElement(By.xpath("//INPUT[@id='identifierId']")).clear();
			driver.findElement(By.xpath("//INPUT[@id='identifierId']")).sendKeys("usuario incorrecto");

			// Boton siguiente
			driver.findElement(By.xpath("//SPAN[@class='RveJvd snByac'][text()='Siguiente']")).click();

			// Validacion cuando no se ingresa un usuario correcto con Explicit wait
			try {
				// Using ExpectedConditions wait until element visibility
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@class='GQ8Pzc']")));
				if (driver.findElement(By.xpath("//DIV[@class='GQ8Pzc']")).isDisplayed()) {
					// Obtener el mensaje de error de la web page
					msgError = driver.findElement(By.xpath("//DIV[@class='GQ8Pzc']")).getText();
					System.out.println("El nombre de usuario es incorrecto");
					if (MSG_ERROR_USER.equals(msgError)) {
						// Nombre de usuario
						driver.findElement(By.xpath("//INPUT[@id='identifierId']")).clear();
						driver.findElement(By.xpath("//INPUT[@id='identifierId']")).sendKeys("usuario correcto");
						// Boton siguiente
						driver.findElement(By.xpath("//SPAN[@class='RveJvd snByac'][text()='Siguiente']")).click();
					}
				}
			} catch (TimeoutException tex1) {
				System.out.println("Hay error de time out 1");

			} finally {
				// No hay error en usuario
				
				// Contraseña
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//INPUT[@type='password']")));
				driver.findElement(By.xpath("//INPUT[@type='password']")).clear();
				driver.findElement(By.xpath("//INPUT[@type='password']")).sendKeys("pass incorrecto");

				// Boton siguiente
				driver.findElement(By.xpath("//SPAN[@class='RveJvd snByac'][text()='Siguiente']")).click();

				try {
					// Using ExpectedConditions wait until element visibility
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@class='xgOPLd']")));
					if (driver.findElement(By.xpath("//DIV[@class='xgOPLd']")).isDisplayed()) {
						// Obtener el mensaje de error de la web page
						msgError = driver.findElement(By.xpath("//DIV[@class='xgOPLd']")).getText();
						System.out.println("La contrasena es incorrecta");
						if (MSG_ERROR_PASS.equals(msgError)) {
							// Contraseña
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//INPUT[@type='password']")));
							driver.findElement(By.xpath("//INPUT[@type='password']")).clear();
							driver.findElement(By.xpath("//INPUT[@type='password']")).sendKeys("pass correcto");
							// Boton siguiente
							driver.findElement(By.xpath("//SPAN[@class='RveJvd snByac'][text()='Siguiente']")).click();
						}
					}
				} catch (TimeoutException tex2) {
					System.out.println("Hay error de time out 2");

				} finally {
					// No hay error en contrasena
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//SPAN[@class='gb_xa gbii']")));
						// Dashboard de usuario
						driver.findElement(By.xpath("//SPAN[@class='gb_xa gbii']")).click();
						// Boton Salir
						driver.findElement(By.xpath("//A[@id='gb_71']")).click();
						//Se completo el escenario 
						result = true;
					} catch (TimeoutException tex3) {
						System.out.println("Hay error de time out 3");
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
