package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalendarSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("Webdriver.chrome.driver", "C:\\selenium"
				+ "\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		WebElement calendar=driver.findElement(By.id("first_date_picker"));
		//Using sendkeys
	    //calendar.sendKeys("02/01/2026"+Keys.ENTER);
		calendar.click();
		//picking on calendar
		WebElement Nextbutton=driver.findElement(By.xpath("//a[@title='Next']"));
		Nextbutton.click();
		WebElement DatetoBeGiven=driver.findElement
		(By.xpath("//*[@id=\'ui-datepicker-div\']/table/tbody/tr[2]/td[3]/a"));
		DatetoBeGiven.click();
		 
	}

}
