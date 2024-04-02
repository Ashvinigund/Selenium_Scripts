package in.bynaric.batu;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.asprise.ocr.Ocr;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GpkExamImgCaptcha {

	static WebDriver driver;
	static String extractedText;
	static WebElement welemv;
	static WebElement CF;

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://www.irctc.co.in/nget/train-search");

		Thread.sleep(3000);
		
		// driver.findElement(By.xpath("//input[@id='username']")).sendKeys("1234");
		// driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		
		try {
		    // Switch the focus to the alert
		    Alert alert = driver.switchTo().alert();

		    // Get the text of the alert
		    String alertText = alert.getText();
		    System.out.println("Alert text is: " + alertText);

		    // Accept the alert (click "OK")
		    alert.accept();
		    
		    // or Dismiss the alert (click "Cancel")
		    // alert.dismiss();

		    // Send text to the alert box
		    // alert.sendKeys("Text to send to the alert");

		    // Switch back to the main window
		   // driver.switchTo().defaultContent();

		} catch (NoAlertPresentException e) {
		    System.out.println("No alert is present on the page.");
		}


		Thread.sleep(3000);
				
		
		
		  //WebElement wait = new WebDriverWait(driver, 10).until(
		         //   ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='myButton']"))
		        //);
			

				
			
			//Click the button
		//	button.click();
		
		//driver.findElement(By.xpath("/html/body/app-root/app-home/div[1]/app-header/div[1]/div[2]/a/i")).click();
        driver.findElement(By.xpath("//button[normalize-space()='LOGIN']")).click();
        
		
		// captcha Image Screenshot
		
		WebElement welemv = driver.findElement(By.xpath("//img[@class='captcha-img']"));

		File src = welemv.getScreenshotAs((OutputType.FILE));
		Thread.sleep(2000);

		// File src = CaptchaImg.getScreenshotAs((OutputType.FILE));
		String path = "C:\\Ashvini_Selenium_Projects\\Random_QP\\CaptchaIMG\\captcha.png";
		FileHandler.copy(src, new File(path));
		Thread.sleep(2000);
		System.out.println("The Screenshot is captured.");

		// Create a new instance of the Ocr class and set its language and image file
		// path
		Ocr.setUp();
		Ocr ocr = new Ocr();
		ocr.startEngine("eng", Ocr.SPEED_FASTEST);
		 final String extractedText = ocr.recognize(new File[] { new File(path) }, Ocr.RECOGNIZE_TYPE_TEXT,
				Ocr.OUTPUT_FORMAT_PLAINTEXT);
		
		System.out.println(extractedText);

		Thread.sleep(3000);
		
		//Captcha field and sending text to Captcha field
				WebElement CF = driver.findElement(By.id("captcha"));
				//CF.sendKeys(extractedText);

				Actions actions = new Actions(driver);
				//actions.moveToElement(CF).click().sendKeys(Keys.chord(Keys.SHIFT, Keys.TAB)).build().perform();
				actions.moveToElement(CF).contextClick(CF).sendKeys(extractedText).click().build().perform();
			     
				
		Thread.sleep(3000);
		
		// driver.findElement(By.xpath("//input[@id='privacyCheck']")).click();
		// driver.findElement(By.xpath("//input[@id='cameraCheck']")).click();

		//Thread.sleep(3000);
		
		// driver.findElement(By.xpath("//button[@id='submit']")).click();

	}

	private static Object WebDriverWait(WebDriver driver2, int i) {
		// TODO Auto-generated method stub
		return null;
	}


	}


