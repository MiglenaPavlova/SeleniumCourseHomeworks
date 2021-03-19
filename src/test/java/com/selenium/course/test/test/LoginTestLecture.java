package com.selenium.course.test.test;

import com.opencsv.exceptions.CsvException;
import com.selenium.course.test.base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CsvReader;

import java.io.IOException;

public class LoginTestLecture extends TestUtil {
   // private WebDriver driver;
    /*@BeforeTest // не може да има бифор тест без тест
    public void setUp(){

        WebDriverManager.chromedriver().setup(); // този начин за стартиране се препоръчва
        driver = new ChromeDriver();
    }*/

    @DataProvider(name = "login-data")
    public static  Object[][] dataProviderHardcodedData(){
        return new Object[][]{
                {"user1", "pass1"},
                {"user2", "pass2"}
        };
    }

   @DataProvider(name = "login-data-file")
    public static  Object[][] dataProviderFromCsvFile() throws IOException, CsvException {
       return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }


    @Test(dataProvider = "login-data-file")
    public void executeSimpleTest(String userName, String password) {
       // driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.name("password"));
        userNameInput.clear();
        passwordInput.sendKeys(password);

        WebElement lgnButton = driver.findElement(By.id("login-button"));
        lgnButton.click();

    }

   /* @AfterTest
    public void tearDown(){
        // driver.close();// samo затваря браузъра
        driver.quit();// затваря и спира selenium
    }*/
}
