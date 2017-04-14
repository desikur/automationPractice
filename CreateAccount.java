/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Desikur
 */
public class CreateAccount {
    
    public CreateAccount() {
    }
    
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://automationpractice.com/";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() {
        driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    }

   
     @Test
     public void createInvalidEmail() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("email_create")).sendKeys("piko.xeramail.com");
     driver.findElement(By.id("SubmitCreate")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     driver.findElement(By.xpath("//li[contains(.,'Invalid email address.')]"));
     }
     
     @Test
     public void createRegisteredEmail() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("email_create")).sendKeys("piko.xera@gmail.com");
     driver.findElement(By.id("SubmitCreate")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     driver.findElement(By.xpath("//li[contains(.,'An account using this email address has already been registered. Please enter a valid password or request a new one.')]"));
     }
     
     @Test
     public void createUnegisteredEmail() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("email_create")).sendKeys("desoy@gmail.com");
     driver.findElement(By.id("SubmitCreate")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     String expurl = "http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";
     String acturl = driver.getCurrentUrl();
         Assert.assertEquals(expurl, acturl);
     }
     
     //------------- Scenario 2----------
     @Test
     public void createAccountEmpty() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("email_create")).sendKeys("desoy@gmail.com");
     driver.findElement(By.id("SubmitCreate")).click();
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     driver.findElement(By.id("submitAccount")).click();
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     //driver.findElement(By.xpath("//li[contains(.,'You must register at least one phone number.')]"));
     //driver.findElement(By.xpath("//li[contains(.,'The Zip/Postal code you've entered is invalid. It must follow this format: 00000')]"));
     driver.findElement(By.xpath("//li[contains(.,'This country requires you to choose a State.')]"));
     }
}
