package PDFAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class QPdownload {
	
	static WebDriver driver;
	static int curInd=0;
	
	public static void main(String[] args) throws Throwable {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions cp = new ChromeOptions();
		//cp.addArguments("--start-fullscreen");
		//cp.addArguments("--headless");
		
		driver = new ChromeDriver(cp);
		Thread.sleep(2000);
		
		driver.get("https://online.dbatuerp.com/dbatu/");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin@bynaric.in");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("dbatuadmin4185#11");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\'side-menu\']/li[11]/a")).click();
		//driver.manage().window().maximize();
		
		
			//try {
				//for (int i = 0; i < 500; i++) {
					//int s=0;
					
				WebElement QPaper = driver.findElement(By.linkText("View Question Paper"));
				
				driver.navigate().to("https://online.dbatuerp.com/dbatu/index_view.php");
				  Thread.sleep(5000);
				// switch to the new tab/window that contains the PDF
				String originalHandle = driver.getWindowHandle();
				for (String handle : driver.getWindowHandles()) {
				    if (!handle.equals(originalHandle)) {
				        driver.switchTo().window(handle);
				        break;
				    }
				}

				// perform the Ctrl+P keyboard action to open the print dialog
				Actions actions = new Actions(driver);
				actions.keyDown(Keys.CONTROL).sendKeys("p").keyUp(Keys.CONTROL).build().perform();

				// wait for the print dialog to load
				WebDriverWait wait = new WebDriverWait(driver, null);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='print-dialog']")));
                Thread.sleep(3000);
				// press the Enter key to save the PDF
				actions.sendKeys(Keys.ENTER).build().perform();
				  Thread.sleep(5000);
				// switch back to the original tab/window
				driver.switchTo().window(originalHandle);

	
					Thread.sleep(5000);
					/*
					if (QPaper.size() == 0)
					{
						System.out.println("all Q paper PDFs are download");
						driver.close();
						System.exit(0);
						
					}
					QPaper.get(0).click();
					System.out.println(curInd+" , "+i);
					//curInd++;
					
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}*/
			
		}
	}

			
			/*
			//Shadow host element
			WebElement host= driver.findElement(By.tagName("pdf-viewer"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			WebElement ShadowDOM1= (WebElement) jse.executeScript("return aurguments[0].shadowRoot", host);
			
			WebElement Toolbar= ShadowDOM1.findElement(By.tagName("viewer-toolbar"));
			WebElement DC= Toolbar.findElement(By.tagName("viewer-download-controls"));
			WebElement button= DC.findElement(By.cssSelector("cr-icon-button#download"));
			button.click();
			
		  // WebElement element= (WebElement) jse.executeScript("return document.querySelector(\"#viewer\").shadowRoot.querySelector(\"#toolbar\").shadowRoot.querySelector(\"#downloads\").shadowRoot.querySelector(\"#download\")");
		  // driver.switchTo().shadow root(element);
		 //  element.click();
		   */
		   
	        //WebElement crIconButton = driver.findElement(By.id("download"));

	        // Execute JavaScript to open the Shadow root of the <cr-icon-button> element
	       // JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	       // WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[2].shadowRoot", crIconButton);
	     
	        // Find the inner element inside the Shadow root
	       // WebElement innerElement = shadowRoot.findElement(By.xpath("//*[@id=\"download\"]"));
	        // Click on the inner element
	      //  innerElement.click();
