package selenium;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class DownloadFile {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub
		System.setProperty("Webdriver.chrome.driver", "C:\\selenium\\"
				+ "chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.tutorialspoint.com/selenium/practice/"
				+ "upload-download.php");
		WebElement downloadLink=driver.findElement(By.linkText("Download"));
		downloadLink.click();
		Thread.sleep(3000);
		File filelocation=new File("C:\\Users\\User\\Downloads");
		File[] Files=filelocation.listFiles();
		for (File file : Files) {
			if(file.getName().equals("sampleFile")) {
				System.out.println("File is downloaded");
				break;
			}
			WebElement UploadFile=driver.findElement(By.id("uploadFile"));
			UploadFile.click();
			//windows to file upload
			Thread.sleep(4000);
			String filess="C:\\Users\\User\\Downloads\\R Thiyagu (1).pdf";
			StringSelection selection= new StringSelection("filess");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			Robot robot= new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(4000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
		} 
		
	}

}
