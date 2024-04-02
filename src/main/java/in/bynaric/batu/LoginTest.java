package in.bynaric.batu;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
static WebDriver driver;
static int curInd=0;

	public static void main(String[] args) throws Throwable {
		
		WebDriverManager.chromedriver().setup();
		
	  ChromeOptions cp = new ChromeOptions();
		//cp.addArguments("--start-fullscreen");
		//cp.addArguments("--headless");
	    cp.addArguments("--no-sandbox");
		cp.addArguments("--disable-dev-shm-usage");
		cp.addArguments("disable-infobars");
		cp.addArguments("--disable-extensions");
		cp.addArguments("--remote-allow-origins=*");
		cp.addArguments("--enable-notifications");
		
		driver = new ChromeDriver(cp);
		Thread.sleep(2000);

		
		// Disable notifications and set headless mode for Chrome browser
		 //ChromeOptions options = new ChromeOptions();
		// options.addArguments("--disable-notifications");
		
		// Create a new instance of the Chrome driver
		 
		
		
		// Navigate to the login page

		driver.navigate().to("https://bynarics.com/dbatu_mis/userindex.php?q=assignTheoryOrder");
		Thread.sleep(3000);

		// Find the username and password fields and enter the credentials
		
		driver.findElement(By.xpath("/html/body/div[2]/ul/li[3]/a")).click();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");

		// Captcha enter

		WebElement Captcha_Element = driver.findElement(By.id("captcha"));

		// Retrieving the captcha text and the two digits to add

		String num1 = driver.findElement(By.xpath("//input[@id='num1']")).getAttribute("value");
		String num2 = driver.findElement(By.xpath("//input[@id='num2']")).getAttribute("value");
		int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
		

		// Entering the sum in the captcha input field
		
		Captcha_Element.sendKeys(String.valueOf(sum));

		Thread.sleep(3000);

		// Find the login button and click it
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Thread.sleep(3000);
		

		driver.navigate().to("https://bynarics.com/dbatu_mis/userindex.php?q=assignTheoryOrder");
		Thread.sleep(3000);
		
		//driver.manage().window().maximize();

		//Click on sendMail
		
		try {
			for (int i = 0; i < 500; i++) {
				//int s=0;
				
			//List<WebElement> numSendMail = driver.findElements(By.xpath("//*[text()=' Send Mail ']"));
			
			List<WebElement> numSendMail = driver.findElements(By.xpath("//*[text()='Send Mail']"));
				Thread.sleep(5000);
				
				if (numSendMail.size() == 0)
				{
					System.out.println("all Mails are sent");
					driver.close();
					System.exit(0);
					
				}
				numSendMail.get(0).click();
				System.out.println(curInd+" , "+i);
				//curInd++;
				
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
