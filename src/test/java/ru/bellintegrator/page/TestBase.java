package ru.bellintegrator.page;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;

    @BeforeEach
    void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "/Users/alxndr/AT School/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @AfterEach
    void afterTest() {
        driver.quit();
    }
}
