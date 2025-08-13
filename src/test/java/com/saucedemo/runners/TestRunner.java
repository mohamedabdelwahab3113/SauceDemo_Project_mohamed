
package com.saucedemo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.saucedemo.stepdefinitions", "com.saucedemo.utils"},
    plugin = {"pretty"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
