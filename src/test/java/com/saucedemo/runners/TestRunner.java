package com.saucedemo.runners;
// Package name - defines the folder structure where this class belongs

import io.cucumber.testng.AbstractTestNGCucumberTests;
// Import Cucumber's base class for running tests with TestNG

import io.cucumber.testng.CucumberOptions;
// Import annotation to configure Cucumber options

// CucumberOptions is used to link feature files, step definitions, and other configurations
@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = {"com.saucedemo.stepdefinitions", "com.saucedemo.utils"}, // Packages containing step definitions & hooks
        plugin = {"pretty"}, // Output format - 'pretty' gives readable console logs
        monochrome = true // Makes console output cleaner by removing special characters
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // This class extends AbstractTestNGCucumberTests to allow running Cucumber with TestNG
    // No methods are needed here; execution is handled by Cucumber & TestNG
}
