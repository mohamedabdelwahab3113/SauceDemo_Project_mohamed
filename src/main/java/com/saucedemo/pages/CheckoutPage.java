package com.saucedemo.pages;
// This package contains all Page Object Model (POM) classes for the SauceDemo project

import org.openqa.selenium.By;
// Used to locate elements on the web page
import org.openqa.selenium.WebDriver;
// Main interface for controlling the browser

/**
 * CheckoutPage class represents the Checkout page of the SauceDemo application.
 * It contains element locators and methods to interact with the checkout process,
 * such as filling personal details, continuing to the next step, and finishing the order.
 * This class follows the Page Object Model (POM) for better maintainability.
 */
public class CheckoutPage {

    private final WebDriver driver;
    // WebDriver instance used to interact with browser

    // Locators for checkout fields and buttons
    private final By firstNameField = By.id("first-name"); // First Name input field
    private final By lastNameField = By.id("last-name");   // Last Name input field
    private final By postalCodeField = By.id("postal-code"); // Postal Code input field
    private final By continueButton = By.id("continue");   // Continue button to go to the next step
    private final By finishButton = By.id("finish");       // Finish button to complete the order
    private final By orderConfirmation = By.className("complete-header"); // Order confirmation message
    private final By errorMessage = By.cssSelector("h3[data-test='error']"); // Error message in case of missing info

    /**
     * Constructor that accepts WebDriver.
     * @param driver WebDriver instance passed from Hooks or test.
     */
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Fills in the checkout form with user details and clicks Continue.
     * @param firstName Customer's first name.
     * @param lastName Customer's last name.
     * @param postalCode Customer's postal code.
     */
    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).clear(); // Clear previous text from First Name
        driver.findElement(firstNameField).sendKeys(firstName); // Enter First Name

        driver.findElement(lastNameField).clear(); // Clear previous text from Last Name
        driver.findElement(lastNameField).sendKeys(lastName); // Enter Last Name

        driver.findElement(postalCodeField).clear(); // Clear previous text from Postal Code
        driver.findElement(postalCodeField).sendKeys(postalCode); // Enter Postal Code

        driver.findElement(continueButton).click(); // Click Continue to proceed
    }

    /**
     * Clicks the Finish button to complete the purchase.
     */
    public void finishOrder() {
        driver.findElement(finishButton).click();
    }

    /**
     * Retrieves the confirmation message after a successful order.
     * @return Confirmation message text.
     */
    public String getOrderConfirmationText() {
        return driver.findElement(orderConfirmation).getText();
    }

    /**
     * Retrieves the error message displayed if form validation fails.
     * @return Error message text.
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
