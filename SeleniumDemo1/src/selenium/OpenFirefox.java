package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenFirefox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("Webdriver.gecko.driver", "C:\\selenium\\geckodriver-v0.36.0-win64\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
		driver.findElement(By.name("q")).sendKeys("Nagapattinama"+Keys.ENTER);
		//driver.quit();
	}

}
