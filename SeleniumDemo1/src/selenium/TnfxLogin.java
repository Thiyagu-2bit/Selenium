package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class TnfxLogin {

	public static void main(String[] args) {
		// Login to tnfx web site
		System.setProperty("Webdriver.chrome.driver", "C:\\selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		try
		{
		driver.get("https://my.tnfx.co/login");
		driver.findElement(By.id("email")).sendKeys("test17test11@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Tomtom17@");
		driver.findElement(By.id("login_btn")).click();
		
		driver.get("https://my.damu.co/login");
		driver.findElement(By.id("email")).sendKeys("test17test11@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Tomtom17@");
		driver.findElement(By.id("login_btn")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div[3]/div/ul[1]/li/a/b")).click();
		
		driver.get("https://client.grasberginternational.com/login");
		driver.findElement(By.id("email")).sendKeys("test17test11@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Tomtom17@");
		driver.findElement(By.id("login_btn")).click();
		
		driver.get("https://crm.roycecapitals.com/login");
		driver.findElement(By.id("email")).sendKeys("thiyaguravi2728@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Thiyagu$123");
		driver.findElement(By.id("login_btn")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div[3]/div/ul[1]/li/a/b")).click();
		driver.findElement(By.xpath("//*[@id=\"skip_d\"]/li/a/b")).click();
		 } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           
	        }
	    }
		}


