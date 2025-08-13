package com.saucedemo.pages;
// This package contains all Page Object Model (POM) classes for the SauceDemo project

import org.openqa.selenium.By;
// Used to locate elements on the page
import org.openqa.selenium.WebDriver;
// Interface to control the browser

/**
 * CartPage class represents the Shopping Cart page of the SauceDemo application.
 * It contains element locators and methods to interact with the cart,
 * such as verifying if a product exists in the cart and clicking the checkout button.
 * This follows the Page Object Model (POM) design for better maintainability and reusability.
 */
public class CartPage {

    private final WebDriver driver;
    // WebDriver instance to control the browser

    // Locator for the Checkout button
    private final By checkoutButton = By.id("checkout");

    /**
     * Constructor to initialize the CartPage with the active WebDriver instance.
     * @param driver WebDriver passed from the test or Hooks class.
     */
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Checks if a specific product is present in the cart.
     * @param productName Name of the product to check (e.g., "Sauce Labs Backpack").
     * @return true if the product exists in the cart, false otherwise.
     */
    public boolean isProductInCart(String productName) {
        // Locator built dynamically to match product name in the cart
        By productLocator = By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']");

        // If findElements returns a non-empty list, the product is present
        return !driver.findElements(productLocator).isEmpty();
    }

    /**
     * Clicks the Checkout button to proceed to the checkout process.
     */
    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }
}
