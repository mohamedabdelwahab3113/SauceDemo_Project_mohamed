package com.saucedemo.pages;
// This package stores all Page Object Model classes for the SauceDemo project

import org.openqa.selenium.By;
// Selenium's By class helps locate web elements
import org.openqa.selenium.WebDriver;
// WebDriver is the main interface for browser automation

/**
 * LoginPage class represents the Login page of the SauceDemo application.
 * It contains locators for login elements and methods to perform login actions.
 * Using the Page Object Model (POM) design pattern, this class helps keep
 * test scripts clean and reusable.
 */
public class LoginPage {

    private final WebDriver driver;
    // WebDriver instance to interact with the browser

    // Locators for web elements on the Login page
    private final By usernameField = By.id("user-name"); // Username text box
    private final By passwordField = By.id("password");  // Password text box
    private final By loginButton = By.id("login-button"); // Login button
    private final By errorMessage = By.cssSelector("h3[data-test='error']"); // Error message display

    /**
     * Constructor that initializes the WebDriver for this page.
     * @param driver the WebDriver instance passed from the Hooks class.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigates directly to the login page URL.
     */
    public void openLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    /**
     * Logs in using provided username and password.
     * @param username The username to enter.
     * @param password The password to enter.
     */
    public void login(String username, String password) {
        driver.findElement(usernameField).clear(); // Clear existing text in username field
        driver.findElement(usernameField).sendKeys(username); // Enter username

        driver.findElement(passwordField).clear(); // Clear existing text in password field
        driver.findElement(passwordField).sendKeys(password); // Enter password

        driver.findElement(loginButton).click(); // Click login button
    }

    /**
     * Retrieves the error message displayed after a failed login attempt.
     * @return The error message text.
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
