package in.bynaric.batu;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.asprise.ocr.Ocr;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExamImgCaptcha_AspriseOCR {

	static WebDriver driver;
	static String extractedText;
	static WebElement welemv;
	static WebElement CF;

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().to("https://gpk.gudexams.com/login");

		Thread.sleep(3000);
		// Party betel ka?
		// driver.findElement(By.xpath("//input[@id='username']")).sendKeys("1234");
		// driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");

		// captcha location
		
		WebElement welemv = driver.findElement(By.xpath("//canvas"));

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
		// extractedText = ocr.recognize(new File[] {src}, Ocr.RECOGNIZE_TYPE_TEXT,
		// Ocr.OUTPUT_FORMAT_PLAINTEXT);

		// Close the browser and print the extracted te
		// driver.quit();
		System.out.println(extractedText);

		Thread.sleep(3000);
		
		
		
		@SuppressWarnings("unused")
		Instant startTime = Instant.now();
		Duration timeout = Duration.ofSeconds(30);

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		            .withTimeout(timeout)
		            .pollingEvery(Duration.ofMillis(500))
		            .ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, Object>() {
			public Object apply(WebDriver driver) {
			    // Perform the desired condition to wait for
				
				
				// find the captcha image element
				WebElement captchaImage = driver.findElement(By.xpath("//canvas"));

				// disable the auto-refresh script
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				WebElement captchaScript = (WebElement) jsExecutor.executeScript(
				        "return arguments[0].nextSibling", captchaImage);
				if (captchaScript != null && captchaScript.getTagName().equalsIgnoreCase("script")) {
				    jsExecutor.executeScript("arguments[0].setAttribute('src', '')", captchaScript);
				}

				
				WebElement CF = driver.findElement(By.id("captcha"));
				//CF.sendKeys(extractedText);

				Actions actions = new Actions(driver);
				//actions.moveToElement(CF).click().sendKeys(Keys.chord(Keys.SHIFT, Keys.TAB)).build().perform();
				actions.moveToElement(CF).contextClick(CF).sendKeys(extractedText).click().build().perform();
			     
				
				//WebElement CF = driver.findElement(By.id("captcha"));
				
				//CF.sendKeys(extractedText);
			    return CF.isDisplayed();
			}
		});

		// add an explicit wait before sending the text to the captcha field
		//WebElement CF = driver.findElement(By.id("captcha"));
		//CF.sendKeys(extractedText);
		//WebDriverWait wait = new WebDriverWait(driver, null);
		//wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(welemv)));
		// captchaField.sendKeys(extractedText);

		// System.exit(0);
		Thread.sleep(3000);
		// driver.findElement(By.xpath("//input[@id='privacyCheck']")).click();
		// driver.findElement(By.xpath("//input[@id='cameraCheck']")).click();

		Thread.sleep(3000);
		// driver.findElement(By.xpath("//button[@id='submit']")).click();

	}

}
