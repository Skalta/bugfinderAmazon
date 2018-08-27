package amazonTest;

import java.util.List;

import io.github.bonigarcia.wdm.ChromeDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Author: Sanjeev Kalta Created : Aug 22, 2018
 */
public class AmazonTest {

	WebDriver driver;

	@Parameters({"searchString"})
	@Test
	public void amazonSearchTest(String searchString)
			throws InterruptedException {

		// Open Chrome browser and navigate to Amazon
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.co.uk/");

		// Type the search String in search bar and search
		driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"))
				.sendKeys(searchString);
		
		driver.findElement(By.xpath("//*[@class='nav-input']")).click();
		

		/*
		 * get the number of listed items in the page and looping through Get
		 * the list items on the page by ID and reading its content
		 */
		List<WebElement> links = driver.findElements(By
				.xpath("//*[@class='s-result-item celwidget  ']"));
		System.out.println("Found :" + links.size()
				+ "Number of Results in page");

		// boolean found = false;
		for (int list_item = 0; list_item <= links.size(); list_item++) {
			String result_num = Integer.toString(list_item);
			String xpath = "result_" + result_num;
			String inside = driver.findElement(
					By.xpath("//*[@id='" + xpath + "']")).getText();

			/*
			 * if the list item content does not contains "Sponsored" click the
			 * H2 element and exit the loop
			 */

			if (!inside.contains("Sponsored")) {
				System.out.println(list_item + " is not sponsered");
				driver.findElement(
						By.xpath("//*[@id='" + xpath + "']"
								+ "/div/div/div/div[2]/div[1]/div[1]/a/h2"))
						.click();
			/*
			 * identify the id element of the add to cart button and click the button.	
			 * User then click the 'Edit Based' button
			 */
				
				driver.findElement(
						By.id("add-to-cart-button"))
						.click();
				driver.findElement(
						By.id("hlb-view-cart-announce"))
						.click();
				
			/*
			 * On the shopping basket page, enter the quantity as 10 and click update
			 * Identify the Delete link and use submit function to delete the item from the basket
			 * 
			 */
				driver.findElement(
						By.xpath("//*[@id=\"a-autoid-0-announce\"]"))
						.click();
				driver.findElement(
						By.xpath("//*[@id=\"dropdown1_9\"]"))
						.click();
				driver.findElement(
						By.xpath("//*[@id=\"activeCartViewForm\"]/div[2]/div/div[4]/div/div[3]/div/div/input"))
						.sendKeys("10");;
				driver.findElement(
						By.xpath("//*[@id=\"a-autoid-1-announce\"]"))
						.click();
				Thread.sleep(2000);
			/* 
			* Click to remove the item from the Cart
			*/
				
				driver.findElement(
						By.xpath("//*[@id=\"activeCartViewForm\"]/div[2]/div/div[4]/div/div[1]/div/div/div[2]/div/span[1]/span/input"))
						.submit();
				
				break;
			}
		}
		driver.close();
	}

}
