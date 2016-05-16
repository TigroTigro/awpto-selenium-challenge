package pl.edu.us.SeleniumChallenge;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Kalkulator {
	private WebDriver driver;
	
	private int multiply(WebDriver driver, int multiplier, int multiplicand, String nazwaSkrina) throws IOException
	{
		driver.get("http://www.anaesthetist.com/mnm/javascript/calc.htm");
		
		WebElement searchInput = driver.findElement(By.name("Display"));
		searchInput.sendKeys(String.valueOf(multiplier));
		
		driver.findElement(By.name("mul")).click();
		searchInput.sendKeys(String.valueOf(multiplicand));
		
		driver.findElement(By.name("result")).click();
		
		File screenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(nazwaSkrina));
		
		return Integer.valueOf(searchInput.getAttribute("value"));
	}
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testMnozenia() throws IOException
	{		
		assertEquals(56, multiply(driver, 7, 8, "skrin.jpg"));
	}
}
