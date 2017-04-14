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
public class login_page {
    
    public login_page() {
    }
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
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
     public void emptyEmailPass() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("SubmitLogin")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     assertTrue("Text error is not found",isTextPresent("error"));
     }
     
     @Test
     public void emptyPass() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("email")).sendKeys("piko@gmail.com");
     driver.findElement(By.id("SubmitLogin")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     driver.findElement(By.xpath("//li[contains(.,'Password is required.')]"));
     //assertTrue("Text error is not found",isTextPresent("email address required"));
     }
     
     @Test
     public void emptyEmail() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("passwd")).sendKeys("doremi");
     driver.findElement(By.id("SubmitLogin")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     driver.findElement(By.xpath("//li[contains(.,'An email address required.')]"));
     }
     
     @Test
     public void wrongPass() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("passwd")).sendKeys("doremi");
     driver.findElement(By.id("email")).sendKeys("piko.xera@gmail.com");
     driver.findElement(By.id("SubmitLogin")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     driver.findElement(By.xpath("//li[contains(.,'Authentication failed.')]"));
     }
     
     @Test
     public void unregisteredEmail() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("passwd")).sendKeys("doremi");
     driver.findElement(By.id("email")).sendKeys("piko@gmail.com");
     driver.findElement(By.id("SubmitLogin")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     driver.findElement(By.xpath("//li[contains(.,'Authentication failed.')]"));
     }
     @Test
     public void signIn() throws Exception{
     driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
     driver.findElement(By.id("passwd")).sendKeys("merdeka");
     driver.findElement(By.id("email")).sendKeys("piko.xera@gmail.com");
     driver.findElement(By.id("SubmitLogin")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     String expurl="http://automationpractice.com/index.php?controller=my-account";
     String acturl=driver.getCurrentUrl();
         Assert.assertEquals(expurl, acturl);
     }
     
     //------ Scenario 2 -- Forgot Password -----
     @Test
     public void forgotInvalidEmail() throws Exception{
     driver.get(baseUrl + "index.php?controller=password");
     driver.findElement(By.id("email")).sendKeys("piko.xeramail.com");
     driver.findElement(By.xpath("//*[@id=\"form_forgotpassword\"]/fieldset/p/button")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     driver.findElement(By.xpath("//li[contains(.,'Invalid email address.')]"));    
     }
     
     @Test
     public void forgotUnregisteredEmail() throws Exception{
     driver.get(baseUrl + "index.php?controller=password");
     driver.findElement(By.id("email")).sendKeys("piko@xeramail.com");
     driver.findElement(By.xpath("//*[@id=\"form_forgotpassword\"]/fieldset/p/button")).click();
     driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
     driver.findElement(By.xpath("//li[contains(.,'There is no account registered for this email address.')]"));    
     }
     
     protected boolean isTextPresent(String s){
         try {
             boolean bul = driver.getPageSource().contains(s);
             return bul;
         } catch (Exception e) {
             return false;
         }
     }
     
     
}
