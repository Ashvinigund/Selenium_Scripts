package DBATU_Result_Process;

import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Result_Process {

	static WebDriver driver;
	static String firebseToken;
	static String messageText;
	static int ind = 0;
	static int curind=1;
	static int result=1;
	
	static String C_code[]= {"11612", "11191", "11372", "11245", "11293", "11242",
            "11370", "11376", "11995", "11246", "11297", "11507"};

	public static void main(String[] args) throws InterruptedException {

		// Driver Configurations
		WebDriverManager.chromedriver().setup();
		ChromeOptions copt = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 1);
		copt.setExperimentalOption("prefs", prefs);
		//copt.addArguments("--remote-debugging-port=9232");
		copt.addArguments("--no-sandbox");
		copt.addArguments("--disable-dev-shm-usage");
		copt.addArguments("disable-infobars");
		copt.addArguments("--disable-extensions");
		copt.addArguments("--remote-allow-origins=*");
		copt.addArguments("--enable-notifications");
		// Driver init
		driver = new ChromeDriver(copt);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Target URL
		driver.navigate().to("https://dbatuerp.com/superLogin");
		Thread.sleep(3000);

		// Login Form
		driver.findElement(By.id("username")).sendKeys("super");
		driver.findElement(By.id("password")).sendKeys("Bynaric@2306");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(4000);
       
		// Configure Firebase Token
		
		driver.findElement(By.linkText("Result")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Process Result")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Message Token']")).click();

		driver.findElement(By.id("name")).sendKeys("QA");
		driver.findElement(By.xpath("//button[normalize-space()='Get New Token']")).click();
		
		while (true) {
			try {
			firebseToken = driver.findElement(By.id("token")).getAttribute("value");
			
			ind++;

			if (firebseToken.isEmpty() == false) {
				driver.findElement(By.xpath("//button[normalize-space()='Save Token']")).click();
				System.out.println(firebseToken);
				break;
			} else if (ind == 1000) {
				//driver.findElement(By.xpath("//button[normalize-space()='Save Token']")).click();
				System.out.println("Firebase token infinite while loop exceed 1000 times");
			}
			}
			catch (Exception e) {
				
			}
		}

		// Result Process
		driver.findElement(By.linkText("Process Result")).click();
		
		// Type
		Thread.sleep(1000);
		selectDropDown("type", 4); // Type Regular
		Thread.sleep(6000);
		selectDropDown("deptId", 1); // Department
		Thread.sleep(4000);
		selectDropDown("processFor", 1); // Process Result For indivdual college
		Thread.sleep(1000);
		selectDropDown("examSeason", 1); // Exam Season
		Thread.sleep(1000);
		selectDropDown("sem", 6); // semester
/*
		List<WebElement> InstDrp= driver.findElements(By.xpath("//*[@id='content']/div[2]/form/div[1]/div[4]/select/option"));
		System.out.println(InstDrp.size());
		//selecting institute
		for (int I = 130; I <=InstDrp.size(); I++)
		{
		 selectDropDown("inst_code", I);
		 System.out.println("inst_code at index " + I + ": " + InstDrp.get(I).getText());
		 Thread.sleep(1000);
		 */
		
		
		//instcodeArray passing
		
		Thread.sleep(3000);
		
		for (int n = 6; n < C_code.length; n++) {
		   // driver.findElement(By.xpath("//select[@name='inst_code']")).sendKeys(inst_code[n]);
		driver.findElement(By.xpath("//select[@name='pid']")).sendKeys(C_code[n]);
		    System.out.println(n);
		    System.out.println("inst_code at index " + curind + ": " + n);
			 Thread.sleep(1000);
			
			/*
		//selecting courses
		List<WebElement> courseDropdown = driver
				.findElements(By.xpath("//*[@id='content']/div[2]/form/div[1]/div[5]/select/option"));

		// for selecting all course
		for (int c = 1; c <=1; c++) {
			Thread.sleep(2000);

			selectDropDown("pid", c);
			Thread.sleep(1000);
			System.out.println("Course_code at index " + c + ": " + courseDropdown.get(c).getText());
			 Thread.sleep(1000);
			
			//driver.findElement(By.name("comment")).sendKeys("B.pharmResultProcessing");
			*/
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[text()='Process Result']")).click();
			Thread.sleep(10000);

			// To check if the notification is appeared or not
			if (driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div")).isDisplayed()) {
				messageText = driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div/span[2]")).getText();
				System.out.println(messageText);

				// to check the result processed successfully or not
				while (true) {
					messageText = driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div/span[2]")).getText();
					if (messageText.equalsIgnoreCase("Result processed successfully")) {
						System.out.println(messageText);
						Thread.sleep(5000);
						break;
						
					}
					else if(! messageText.equalsIgnoreCase("Result processing is started"))
					{
						//System.out.println("Error caught on " + c + " index, message:- " + "  -----  " + driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div/span[2]")).getText());
						Thread.sleep(6000);
						break;
				}
						
				}
				
				//driver. findElement(By.xpath("//select[@name='inst_code']")).clear();
				
			}	
		}
		driver. findElement(By.xpath("//select[@name='pid']")).clear();
		curind++;
		}
		
	//}
		
	
		
	public static void selectDropDown(String name, int index) {
		WebElement selElem = driver.findElement(By.name(name));
		Select sel = new Select(selElem);
		sel.selectByIndex(index);

	}

}



