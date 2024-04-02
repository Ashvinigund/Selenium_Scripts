/**
 * 
 */
package in.bynaric.batu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 *Nov 22, 2022 
 *@author Suraj Kathare
 *@version 1.0
 */
public class GetResultData
{
	static WebDriver driver;
	static int s=102;
	static int courseData[] = {11370, 12191, 11356, 11297, 11298, 12507, 12196, 12699, 12901, 11503, 16032, 13823, 11602, 11461, 11245, 11507, 11191, 11242, 11293, 11376, 11372, 11466, 11246, 11612, 11527, 11914, 11624, 18507, 18245, 18293, 18372, 18466, 18246, 18527, 18501, 11995, 11918, 15101, 14126, 20441, 14817, 14822, 14821, 12632, 12375, 12210, 12245, 12242, 12915, 12294, 12216, 12393, 12601, 12370, 12380, 12293, 12376, 12372, 12292, 12353, 12596, 12619, 12904, 12608, 12612, 12616, 12613, 12212, 12260, 12914, 12610, 12341, 12197, 17032, 11257, 11263, 11464, 11900, 11911, 11913, 11916, 11922, 11925, 11994, 19115, 19116, 19117, 19118, 14125, 14818, 14819, 12938, 12939, 12951, 13824, 11504, 12956, 112, 11844, 15100, 19102, 19103, 19105, 19106, 19109, 19111, 19113, 17037, 17210, 12614, 11575, 11576, 11625, 11701, 11534, 17047, 12604, 12605, 12919, 19114, 19107, 19101, 19112, 19110, 19108, 19104, 19106, 19109, 19111, 19113, 17037, 17210, 12614, 11575, 11576, 11625, 11701, 11534, 17047, 12604, 12605, 12919, 19114, 19107, 19101, 19112, 19110, 19108, 19104};

	@SuppressWarnings("deprecation")
	public static void main(String [] args) throws InterruptedException
	{
		//cmd chrome.exe �remote-debugging-port=9222 �user-data-dir=E:\ChromeData

		WebDriverManager.chromiumdriver().setup();
		ChromeOptions opt = new ChromeOptions();

		opt.setExperimentalOption("debuggerAddress","localhost:9222");

		driver=new ChromeDriver(opt);
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		System.out.println("array lenght is:- "+courseData.length);

		for(int k=0; k<courseData.length;k++)
		{
			Thread.sleep(3000);

			driver.findElement(By.xpath("//button[@data-bs-toggle='collapse']")).click();

			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@value='Select Exam Season']")).click();

			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/div/div/div/div/table/tbody/tr[3]/td[1]/span/a")).click();

			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@value='Select Course']")).click();

			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@placeholder='Course Code']")).click();
			driver.findElement(By.xpath("//input[@placeholder='Course Code']")).sendKeys(""+courseData[s]);
			System.out.println("Current index of 's' is :- "+s);
			System.out.println("Current value of 's' is :- "+ courseData[s]);
			s++;

			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/div/div/div/div/div[1]/div/table/tbody/tr/td[2]/div/span[1]/input")).click();

			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[text()='Select']")).click();

			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@value='Search']")).click();

			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@value='Email CSV']")).click();
			Thread.sleep(4000);

			driver.navigate().to("https://uni.dbatuapps.in/dig_result_export?mode=export");
			Thread.sleep(3000);
		}
	}

}
