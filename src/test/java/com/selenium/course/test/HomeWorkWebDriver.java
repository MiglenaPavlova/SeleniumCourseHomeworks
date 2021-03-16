package com.selenium.course.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class HomeWorkWebDriver {

    private WebDriver driverChrome;
    private WebDriver driverFirefox;

    @BeforeTest

    public void openBrowser(){
        WebDriverManager.chromedriver();
        driverChrome = new ChromeDriver();

        WebDriverManager.firefoxdriver();
        driverFirefox = new FirefoxDriver();

    }

    @Test

    public void executeTestChrome(){
        driverChrome.get("https://www.saucedemo.com/");

        WebElement userNameField = driverChrome.findElement(By.id("user-name"));
        userNameField.sendKeys("standard_user");

        WebElement passwordField = driverChrome.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        WebElement loginButton = driverChrome.findElement(By.id("login-button"));
        loginButton.click();

        WebElement sortMenuSelect = driverChrome.findElement(By.className("product_sort_container"));
        Select dropdownOption = new Select(sortMenuSelect);
        dropdownOption.selectByVisibleText("Price (high to low)");

        WebElement addToCart = driverChrome.findElement(By.xpath(
                "//div[text()='Sauce Labs Fleece Jacket']//ancestor::div[@class='inventory_item']//button"));
        addToCart.click();

        WebElement addToCart2 = driverChrome.findElement(By.xpath(
                "//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item']//button"));
        addToCart2.click();

        WebElement badge = driverChrome.findElement(By.cssSelector(".shopping_cart_badge"));
        assertEquals(badge.getText(), "2");

    }


    @Test
    public void executeTestFirefox(){
        driverFirefox.get("https://www.saucedemo.com/");

        WebElement userNameField = driverFirefox.findElement(By.id("user-name"));
        userNameField.sendKeys("standard_user");

        WebElement passwordField = driverFirefox.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        WebElement loginButton = driverFirefox.findElement(By.id("login-button"));
        loginButton.click();

    }

    @AfterTest

    public void tearDown(){
        driverChrome.quit();
        driverFirefox.quit();
    }

}

