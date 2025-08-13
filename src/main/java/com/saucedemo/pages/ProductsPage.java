package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private final WebDriver driver;

    private final By productsTitle = By.className("title");
    private final By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductsTitleDisplayed() {
        return driver.findElement(productsTitle).isDisplayed();
    }

    public void addProductToCart(String productName) {
        By productButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.findElement(productButton).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}