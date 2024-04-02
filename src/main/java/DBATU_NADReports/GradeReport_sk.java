package DBATU_NADReports;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class GradeReport_sk 
{

	static WebDriver driver;
	static int curInd = 43;
	
	static String instInput[] = {"1164","1281","1288","1290","2130","2254","2555","2567","2572","2578","2580","2581","2582","2584","2587","2588","2589","2590","2591","2592","2596","2597","2602","2603","2616","2619","3486","3490","3493","3500","3504","4271","4651","4656","4658","4659","4660","5281","5286","5424","5428","5430","5439","5446","5448","5450","5454","5455","5458","5459","5461","5462","5464","5466","5467","5468","5469","5471","5473","5474","5477","5478","5479","6381","6390","6391","6395","6398","6504","6510","6757","6827","6829","6886","6900","6902","6905","6906","6908","6910","6914","6915","6923","6927","6930","6931","6933","6937","6942","6943","6951","6952","6956","6957"};
	
	public static void main(String args[]) throws InterruptedException
	{
		
		System.out.println("array lenght is:- " + instInput.length);
		ChromeOptions cp = new ChromeOptions();
		cp.addArguments("--maximize");
		cp.addArguments("force-device-scale-factor=0.90");
		cp.addArguments("high-dpi-support=0.70");
		
		//cp.addArguments("--headless");
		
		driver = new ChromeDriver(cp);
		Thread.sleep(2000);
		
		// Target URL
		driver.navigate().to("https://datahanddb2223.bynaricexam.com/");
		Thread.sleep(9000);
	
		//a[normalize-space()='University Login']
		//driver.findElement(By.xpath(" //button[@id='dropdown-basic']")).click();
		
		driver.findElement(By.id("dropdown-basic")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[normalize-space()='University Login']")).click();
		Thread.sleep(5000);
		
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		// Scroll down to the bottom of the page
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("username")).sendKeys("ashwini");
		Thread.sleep(3000);
		driver.findElement(By.id("password")).sendKeys("@&hw!n!39");
		
		//driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
		Thread.sleep(5000);
		/*
		System.out.println("enter Otp");
		
		Scanner OTP = new Scanner(System.in);

		String scanner = OTP.nextLine();
		Thread.sleep(5000); */
		
		driver.findElement(By.xpath("//input[@placeholder='OTP']")).sendKeys("112732");
		
		Thread.sleep(5000);
		
		System.out.println("captcha");
		 
		Scanner captcha = new Scanner(System.in);

		String scanner1 = captcha.nextLine();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='captcha']")).sendKeys(scanner1);
		
		driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
		Thread.sleep(7000);
		
		driver.findElement(By.xpath("//a[normalize-space()='Result']")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Download Grade Card")).click();
		Thread.sleep(3000);
		// Type
				
				//selectDropDown("type", 5); // Type Regular
				//Thread.sleep(5000);
				selectDropDown("dept_id", 3); // Department
				Thread.sleep(10000);
				//selectDropDown("inst_code", 3); // Process Result For indivdual college
				Thread.sleep(10000);
				selectDropDown("season", 28); // Exam Season
				Thread.sleep(10000);
				selectDropDown("sem", 7); // semester
				Thread.sleep(10000);
				selectDropDown("courseCode", 1);
				
				
				
				Thread.sleep(3000);
				//List<WebElement> instElem = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/form/div[1]/div[4]/select/option"));
				//int ind1=50;
				
				for(int i=0;i < instInput.length;i++)
				{
					Thread.sleep(10000);
					
					driver.findElement(By.xpath("//select[@name='inst_code']")).click();
					driver.findElement(By.xpath("//select[@name='inst_code']")).sendKeys(instInput[curInd]);
					Thread.sleep(3000);
					System.out.println(instInput[curInd]);
					System.out.println(curInd);
					
					
					//driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/form/div[1]/div[4]/select/option["+ind1+"]")).click();
					//selectDropDownXpath("//*[@id='content']/div[2]/form/div[1]/div[2]/select/option", i+1);
					Thread.sleep(9000);
					if(i==0)
					{
					driver.findElement(By.xpath("//input[@id='flexCheckDefault']")).click();
				   
					}
					//click on download
					Thread.sleep(15000);
					driver.findElement(By.xpath("//button[normalize-space()='Download']")).click();
					
					Thread.sleep(15000);
					
					while(true)
					{
						Thread.sleep(9000);
						List<WebElement> message= driver.findElements(By.xpath("//font[text()='All PDF Done']"));
						if(message.size() !=0)
						{
							Thread.sleep(9000);
							driver.findElement(By.xpath("//button[normalize-space()='Remove Message']")).click();
						break;
						}
					
					}
					System.out.println("I am out of whil loop and incrementing the i++");
					//ind1++;
					curInd++;
					//driver.findElement(By.xpath("//input[@id='flexCheckDefault']")).clear();
					Thread.sleep(5000);
					
				}
	
				
	}
	
	public static void selectDropDown(String name, int index) {
		WebElement selElem = driver.findElement(By.name(name));
		Select sel = new Select(selElem);
		sel.selectByIndex(index);
	}
	
	
}
