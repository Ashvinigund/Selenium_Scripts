package in.bynaric.batu;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Aapplesarkar {
	
	static WebDriver driver;
	 static int a=0;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Throwable {
	WebDriverManager.chromedriver().setup();
	ChromeOptions opt = new ChromeOptions();

	//opt.setExperimentalOption("debuggerAddress", "chrome.exe --remote-debugging-port=9222");
	opt.addArguments("--remote-debugging-port=9222");
	driver = new ChromeDriver(opt);

	driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

	/*
	* List<WebElement> cnt = driver.findElements(By.id("btnScrutiny"));
	* System.out.println("Size Is:- " + cnt.size());
	*/

	for (int s = 0; s < 5000; s++)
	{
	List<WebElement> cnt = driver.findElements(By.id("btnScrutiny"));
	System.out.println("Size Is:- " + cnt.size());

	Thread.sleep(3000);
	cnt.get(5).click();

	Thread.sleep(3000);

	driver.findElement(By.id("chkSelectAll")).click();

	Thread.sleep(2000);

	driver.findElement(By.id("ScrutinyRemark")).sendKeys("Approved");

	Thread.sleep(2000);

	driver.findElement(By.xpath("//button[text()='Application Forward']")).click();

	Thread.sleep(4000);

	driver.switchTo().alert().accept();

	Thread.sleep(4000);
	driver.findElement(By.name("OK")).click();
	a++;
	System.out.println(a);
	Thread.sleep(4000);
	}
	}

}
