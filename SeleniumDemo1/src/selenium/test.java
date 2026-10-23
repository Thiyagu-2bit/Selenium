package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriver driver=new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
		
		WebElement userName= driver.findElement(By.id("username"));
		userName.sendKeys("student");
		WebElement password= driver.findElement(By.id("password"));
		password.sendKeys("Password123");
	}

}
