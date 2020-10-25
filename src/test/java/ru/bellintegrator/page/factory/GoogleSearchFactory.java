package ru.bellintegrator.page.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GoogleSearchFactory {

    private WebDriver driver;

    @FindBy(how = How.CLASS_NAME, className = "q")
    WebElement inputField;

    @FindBy(how = How.CLASS_NAME, className = "btnK")
    WebElement searchButton;

    @FindAll(@FindBy(how = How.XPATH, using = "//*[@class=\"TbwUpd NJjxre\"]//*[@class=\"eipWBe\"]"))
    List<WebElement> articles;

    public GoogleSearchFactory(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://www.google.com/");

    }

    public void findLinks(String key) {
        this.inputField.click();
        this.inputField.sendKeys(key);
        this.searchButton.click();
    }

    public List<WebElement> getLinks() {
        return this.articles;
    }
}
