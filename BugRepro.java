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
 * @author desikur
 */
public class BugRepro {
    
    public BugRepro() {
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
     public void bugQuantity() throws Exception{
         driver.get(baseUrl + "index.php?id_product=1&controller=product");
         driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
         driver.findElement(By.xpath(".//*[@id='quantity_wanted']")).sendKeys("10000");
         driver.findElement(By.xpath(".//*[@id='add_to_cart']/button")).click();
         driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
         driver.findElement(By.linkText("Proceed to checkout")).click();
     }
     
     @Test
     public void bugContinueShopping() throws Exception{
         driver.get(baseUrl + "index.php?id_product=1&controller=product");
         driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
         driver.findElement(By.xpath(".//*[@id='add_to_cart']/button")).click();//add to cart
         driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
         driver.findElement(By.linkText("Proceed to checkout")).click();//proceed to checkout1
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.findElement(By.linkText("Proceed to checkout")).click();//summary page
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.findElement(By.id("passwd")).sendKeys("merdeka");
         driver.findElement(By.id("email")).sendKeys("piko.xera@gmail.com");
         driver.findElement(By.id("SubmitLogin")).click();//login page
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         //driver.findElement(By.linkText("Continue shopping")).click();//address page
         driver.findElement(By.xpath(".//*[@id='center_column']/p[2]/a[2]")).click();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.findElement(By.linkText("Continue shopping")).click();//summary page
         String cururl = driver.getCurrentUrl();
         String acturl = "http://automationpractice.com/index.php?id_product=1&controller=product";
         Assert.assertEquals(acturl, cururl);
     }
}
