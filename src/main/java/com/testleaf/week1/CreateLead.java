package com.testleaf.week1;

import java.time.Duration;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateLead {
        @Test
	public  void createLead() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
        // 1. Navigate to Salesforce login page
        driver.get("https://login.salesforce.com");

        // 2. Login
        driver.findElement(By.xpath("//input[@id='username']"))
                .sendKeys("dilipkumar.rajendran@testleaf.com");
        driver.findElement(By.xpath("//input[@id='password']"))
                .sendKeys("TestLeaf@2025");
        driver.findElement(By.xpath("//input[@id='Login']")).click();


        // 3. Click App Launcher
        driver.findElement(By.xpath("//button[@title='App Launcher']")).click();

        
        //Thread.sleep(3000);
        // 4. Click View All
       WebElement viewAll= driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
               wait.until(ExpectedConditions.visibilityOf(viewAll));
       viewAll.click();
      
       // 5. Search and click Leads
        driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']"))
                .sendKeys("Leads");
         WebElement leadslink  = driver.findElement(By.xpath("//mark[text()='Leads']"));
                wait.until(ExpectedConditions.visibilityOf(leadslink));
       leadslink.click();
         // 6. Click New Lead
        driver.findElement(By.xpath("//div[@title='New']")).click();

        // 7. Fill Lead details
        driver.findElement(By.xpath("//button[@name='salutation']")).click();

        driver.findElement(By.xpath("//span[@title='Mr.']")).click();

        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Babu");
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Manickam");
        driver.findElement(By.xpath("//input[@name='Company']")).sendKeys("Testleaf");

        // 8. Save Lead
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

        // 9. Verify Lead creation using toast message
        WebElement toastMessage = driver.findElement(
                By.xpath("//span[contains(@class,'forceActionsText')]"));

        String toastText = toastMessage.getText();

        if (toastText.contains("was created")) {
                System.out.println("PASS: Lead created successfully");
        } else {
                System.out.println("FAIL: Lead creation failed");
        }
        driver.quit();
}
}
