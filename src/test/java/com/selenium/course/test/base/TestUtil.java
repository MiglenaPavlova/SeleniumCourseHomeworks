package com.selenium.course.test.base;


import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtil {
    public   WebDriver driver;
    private String url;
    private int implicitWait;
    private String browser;

    @BeforeSuite
    public void readConfigProperties(){
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties"))
        {
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitWait = Integer.parseInt(config.getProperty("implicitlyWait"));
            browser = config.getProperty("browser");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @BeforeTest // не може да има бифор тест без тест
    public void setUp(){

        setupBrowserDriver();
        loadUrl();
        /*WebDriverManager.chromedriver().setup(); // този начин за стартиране се препоръчва
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");*/
    }

    private void loadUrl(){
        driver.get(url);
    }

    private void  setupBrowserDriver() {
        //homework 19.03 open browser for Chrome
        switch (browser) {
            case "firefox":
                driver = DriverFactory.getFirefoxDriver(implicitWait);
                break;
            case "chrome":
                driver = DriverFactory.getChromeDriver(implicitWait);
                break;
            default:
                System.out.println("Browser is not correct");
                //за домашно да добавим за хром в зависимост от стойността в config.properties
        }
    }

    @AfterTest
    public void tearDown(){

        // driver.close();// samo затваря браузъра
        driver.quit();// затваря и спира selenium
    }
}
