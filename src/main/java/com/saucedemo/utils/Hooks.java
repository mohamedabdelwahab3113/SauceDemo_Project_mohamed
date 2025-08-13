package hooks.utils;
// Package name - defines where this class is located in the project folder structure

import io.cucumber.java.After;
// Cucumber annotation to run a method after each scenario
import io.cucumber.java.Before;
// Cucumber annotation to run a method before each scenario
import org.openqa.selenium.WebDriver;
// Selenium interface for controlling the browser
import io.github.bonigarcia.wdm.WebDriverManager;
// WebDriverManager automatically manages driver binaries (not used yet here)
import org.openqa.selenium.edge.EdgeDriver;
// Selenium class for controlling Microsoft Edge browser

public class Hooks {
    // Hooks class manages setup and teardown for WebDriver in Cucumber tests

    private static WebDriver driver;
    // Static WebDriver so the same driver instance can be accessed across the project

    @Before
    public void setUp() {
        // Runs before each scenario starts
        driver = new EdgeDriver(); // Creates a new Edge browser instance
        driver.manage().window().maximize(); // Maximizes browser window
        driver.get("https://www.saucedemo.com/"); // Opens the SauceDemo login page
    }

    @After
    public void tearDown() {
        // Runs after each scenario ends
        if (driver != null) { // Checks if driver is still active
            driver.quit(); // Closes the browser and ends the session
        }
    }

    public static WebDriver getDriver() {
        // Provides global access to the WebDriver instance
        return driver;
    }
}
