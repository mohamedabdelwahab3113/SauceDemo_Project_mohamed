package com.saucedemo.pages;
// Package location for the page classes (Page Object Model)

import org.openqa.selenium.By;
// Selenium class for locating elements
import org.openqa.selenium.WebDriver;
// Selenium interface for controlling the browser

public class ProductsPage {
    // Represents the Products Page in SauceDemo, following the Page Object Model

    private final WebDriver driver;
    // WebDriver instance to interact with the browser

    // Locator for the "Products" title on the page
    private final By productsTitle = By.className("title");

    // Locator for the shopping cart icon
    private final By cartIcon = By.className("shopping_cart_link");

    // Constructor - receives the WebDriver instance from the test
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Checks if the "Products" title is displayed on the page
    public boolean isProductsTitleDisplayed() {
        return driver.findElement(productsTitle).isDisplayed();
    }

    // Adds a specific product to the cart by product name
    public void addProductToCart(String productName) {
        // Build a dynamic XPath to locate the button for the given product
        By productButton = By.xpath(
                "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button"
        );
        driver.findElement(productButton).click(); // Click the "Add to cart" button
    }

    // Navigates to the shopping cart page
    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}
