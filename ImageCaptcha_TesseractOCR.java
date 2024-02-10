import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class ImageCaptcha_TesseractOCR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		try {
		//driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://gpk.gudexams.com/login");
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("super");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
		
		//Load Captcha Image
		WebElement CaptchaImg =driver.findElement(By.xpath("//div[@class='style_captchaContainer__LdFYB']"));
		Thread.sleep(2000);
		
		File screenshot = CaptchaImg.getScreenshotAs(OutputType.FILE);
		String Path="C:\\\\Ashvini_Selenium_Projects\\\\ImageCaptcha\\\\ScreenshotImg\\\\screenshot.png";
		
		 FileUtils.copyFile(screenshot, new File(Path));
		 Thread.sleep(2000);
		 
		  System.out.println("Screenshot saved to /path/to/screenshot.png");
		    
		      
		    
		// Use Tesseract OCR to recognize the numbers in the captcha image
		ITesseract tesseract = new Tesseract();
		
		//System.setProperty("TESSDATA_PREFIX", "C:\\Ashvini_Selenium_Projects\\ImageCaptcha\\TessData\\eng.traineddata");
		//tesseract.setDatapath("C:\\Ashvini_Selenium_Projects\\ImageCaptcha\\TessData\\eng.traineddata");
		
		String captchaText = tesseract.doOCR(screenshot);
		//String text = gettext

		// Extract the numbers from the recognized text
		//String Captcha = captchaText.split("below")[1].replaceAll("[^a-zA-Z0-9]", "");
		
		
		System.out.println("image OCR done");
		
		System.out.println(captchaText);
		
		}
		catch(Exception e) {
			System.out.println("exception caught :" +e.getMessage());
		}
	
	}


	}


