package selenium;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoComplete {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("Webdriver.chrome.driver", "C:\\selenium"
				+ "\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		WebElement input=driver.findElement(By.id("autocomplete"));
		input.sendKeys("Ind");
		Thread.sleep(4000); //
		List<WebElement> optionList= driver.findElements
				(By.xpath("//*[@id=\'ui-id-1\']/li"));
		System.out.println(optionList.size());
		for (WebElement webElement : optionList) {
			if( webElement.getText().equals("India")){
				webElement.click();
				System.out.println("Selecting"+webElement.getText());
				break;
				}
				}
	}

}
