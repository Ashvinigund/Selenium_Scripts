package cpu_utilization;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class dynamic_table {

	static WebDriver driver;

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--enable-notifications");
		opt.addArguments("--maximize");
		driver = new ChromeDriver(opt);
		Thread.sleep(3000);
		
		
		driver.navigate().to("http://uitestingplayground.com");
		driver.findElement(By.linkText("Dynamic Table")).click();
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,450)", "");
		 
		 
		 List <WebElement> col = driver.findElements(By.xpath("/html/body/section/div/div/div[2]/div/span"));
	        System.out.println("No of cols are : " +col.size()); 
	        
	        //No.of rows 
	        List <WebElement> rows = driver.findElements(By.xpath("/html/body/section/div/div/div[3]/div")); 
	        System.out.println("No of rows are : " + rows.size());

	        WebElement cpu=driver.findElement(By.xpath("//*[contains(text(),'Chrome CPU')]"));
	     // Get the text of the element
	        String text = cpu.getText();

	        // Print the text
	        System.out.println(text);

	        // Close the browser
	        //driver.quit();
	     
	       
	        
	        
	        
	        //To calculate no of rows In table.
	    	int rows_count = rows.size();
	    	
	    	//Loop will execute till the last row of table.
	    	for (int row = 0; row < rows_count; row++) {
	    	    //To locate columns(cells) of that specific row.
	    	  List < WebElement > Columns_row = rows.get(row).findElements(By.xpath("//*[contains(text(),'Chrome')]"));
	    
	    	   //To calculate no of columns (cells). In that specific row.
	    	    int columns_count = Columns_row.size();
	    	    
	    	    System.out.println("Number of cells In Row " + row + " are " + columns_count);
	    	    //Loop will execute till the last cell of that specific row.
	    	    
	    	    for (int column = 0; column < columns_count; column++) {
	    	        // To retrieve text from that specific cell.
	    	        String celtext = Columns_row.get(column).getText();
	    	        System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
	    	   // System.out.println("-------------------------------------------------- ");
	        
	    	}
	    	} 
	}

}
