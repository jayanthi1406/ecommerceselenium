package com.example.ecommerceapplicationselenium;


import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        String chromeBinary = System.getenv("CHROME_BIN");

        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        driver = new ChromeDriver(options);

        String page = new File("webpage/login.html")
                .getAbsoluteFile()
                .toURI()
                .toString();

        driver.get(page);
    }

    // Test Case 1: Valid Login
    @Test
    public void validLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("student");

        driver.findElement(By.id("password"))
                .sendKeys("12345");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message"))
                .getText();

        System.out.println("Login Message: " + message);

        Assert.assertEquals(message, "Login Successful");
    }

    // Test Case 2: Product Search
    @Test
    public void productSearchTest() {

        driver.findElement(By.id("searchBox"))
                .sendKeys("Laptop");

        driver.findElement(By.id("searchButton")).click();

        String result = driver.findElement(By.id("searchResult"))
                .getText();

        System.out.println("Search Result: " + result);

        Assert.assertEquals(result, "Laptop Found");
    }

    // Test Case 3: Add Product to Cart
    @Test
    public void addToCartTest() {

        driver.findElement(By.id("addToCartButton")).click();

        String message = driver.findElement(By.id("cartMessage"))
                .getText();

        System.out.println("Cart Message: " + message);

        Assert.assertEquals(message, "Product Added to Cart");
    }

    // Test Case 4: Verify Cart Details
    @Test
    public void verifyCartTest() {

        driver.findElement(By.id("addToCartButton")).click();

        String cartCount = driver.findElement(By.id("cartCount"))
                .getText();

        System.out.println("Cart Count: " + cartCount);

        Assert.assertEquals(cartCount, "1");
    }

    // Test Case 5: Checkout Functionality
    @Test
    public void checkoutTest() {

        driver.findElement(By.id("addToCartButton")).click();

        driver.findElement(By.id("checkoutButton")).click();

        String message = driver.findElement(By.id("checkoutMessage"))
                .getText();

        System.out.println("Checkout Message: " + message);

        Assert.assertEquals(message, "Order Placed Successfully");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}

