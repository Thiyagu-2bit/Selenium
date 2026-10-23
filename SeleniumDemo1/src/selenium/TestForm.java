package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class TestForm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("Webdriver.chrome.driver", "C:\\selenium\\"
				+ "chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://automationintesting.com/selenium/testpage/");
		driver.findElement(By.id("firstname")).sendKeys("thiyagu");
		driver.findElement(By.id("surname")).sendKeys("Ravichandran");
		//webelement create panni irukkom
		WebElement dropDown=driver.findElement(By.id("gender"));
		Select select= new Select(dropDown);
		select.selectByIndex(1);
		WebElement Favorite =driver.findElement(By.name("colour"));
		Favorite.click();
		WebElement Contact =driver.findElement(By.id("checkbox1"));
		Contact.click();
		WebElement TellMe= driver.findElement(By.xpath("//*[@id=\'contactus\']"
				+ "/div[5]/div/label/textarea"));
		TellMe.sendKeys("I am good tester");
		//doubt
		/*WebElement Country=driver.findElement(By.id("continent"));
		Select selectbox= new Select(Country);
		selectbox.selectByVisibleText("asia");*/
		
		
}
}
