package com.spicejet._spicejet;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.ReadExcel;
import util.TakeShot;

public class AppTest {
	WebDriver driver;

	@BeforeClass
	public void callDriver() {

		String Driverpath = "D:\\INSTALL\\QAE\\eclipse_Workspace\\848850_spicejet\\Driver\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", Driverpath);
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(priority = 0)
	public void openPage() {
		String url = "https://www.spicejet.com/";
		driver.get(url);
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "SpiceJet - Flight Booking for Domestic and International, Cheap Air Tickets";
		Assert.assertEquals(ExpectedTitle, ActualTitle);
	}

	@Test(priority = 1)
	public void fillFromTo() throws IOException, InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_rbtnl_Trip\"]/tbody/tr/td[2]/label")).click();
		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_originStation1_CTXT\"]")).click();

		String filePath = "D:\\INSTALL\\QAE\\eclipse_Workspace\\848850_spicejet";
		String fileName = "Location.xlsx";
		String sheetName = "Sheet1";
		String from = null;

		from = ReadExcel.readExcel(filePath, fileName, sheetName, 0);

		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_originStation1_CTXT\"]")).sendKeys(from);

		Thread.sleep(4000);

		String To = null;

		To = ReadExcel.readExcel(filePath, fileName, sheetName, 1);

		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_destinationStation1_CTXT\"]")).sendKeys(To);

	}

	@Test(priority = 2)
	public void clander() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_view_date1\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_view_date1\"]")).sendKeys("24/05/2021");
		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[5]/td[2]/a")).click();
		Thread.sleep(4000);

	}

	@Test(priority = 3)
	public void Passanger() throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id=\"divpaxinfo\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_Adult\"]")).click();
		Select adult = new Select(driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddl_Adult\"]")));
		adult.selectByValue("2");
		Thread.sleep(4000);		

	}

	@Test(priority = 4)
	public void Search() throws InterruptedException {
		// Search
		driver.findElement(By.xpath("//*[@id=\"ctl00_mainContent_btn_FindFlights\"]")).click();
		Thread.sleep(4000);
		int min = 50;
	     int max = 1000;
		int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		String fileWithPath = "D:\\INSTALL\\QAE\\eclipse_Workspace\\848850_spicejet\\Screensort\\Report"+ random_int +".png";
		try {
			TakeShot.takeSnapShot(driver, fileWithPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority = 5)
	public void printNo() {
		
		//As no fares available 
		String s=driver.findElement(By.xpath("//*[@id=\"selectMainBody\"]/div[6]/div")).getText();
		System.out.println(s);
	}

	@AfterClass
	public void closeDriver() {
		 driver.close();
	}
}
