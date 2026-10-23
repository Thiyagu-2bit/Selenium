package selenium;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class TableExample {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("Webdriver.chrome.driver", "C:\\selenium"
				+ "\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.tutorialspoint.com/selenium/practice/webtables.php");
		//column count
		List<WebElement> columns=(List<WebElement>) driver.findElements(By.tagName("th"));
		int columncount=columns.size();
		System.out.println("Number of columns "+ columncount);
		//row count
		List<WebElement> rows=(List<WebElement>) driver.findElements(By.tagName("tr"));
		int rowcount=rows.size();
		System.out.println("Number of rows "+ rowcount);
		//create Xpath
		WebElement getage=driver.findElement
		(By.xpath("//td[normalize-space()='Alden']//following::td[2]"));
		String Age=getage.getText();
		System.out.println("Alden Age is " +Age);
		WebElement getsalary=driver.findElement
		(By.xpath("//td[normalize-space()='Kierra']//following::td[4]"));
		String Salary=getsalary.getText();
		System.out.println("Kierra Salary is " +Salary);
	}

}
