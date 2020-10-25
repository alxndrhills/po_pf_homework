package ru.bellintegrator.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoogleSearch {

    WebDriver driver;

    private String selectorSearchItems="//div[@class='g']";
    private String selectorURL = ".//div[@class='r']/a[@href]";
    private String selectorNamePage = ".//div[@class='r']/a[@href]";
    private String selectorDescription = ".//div[@class='s']";
    private List<WebElement> searchItems = new ArrayList<>();
    private List<Map<String, Object>> collectionResults = new ArrayList<>();

    public GoogleSearch(WebDriver driver, String searchKey) {
        this.driver = driver;
        this.driver.get("https://www.google.com/search?q=" + searchKey);
        this.searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }

    public List<Map<String, Object>> getCollectionResults() {
        for (WebElement result: searchItems) {
            this.collectionResults.add(Map.of("WEB_ELEMENT",result,
                    "URL",result.findElement(By.xpath(selectorURL)).getText(),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText(),
                    "DESCRIPTION", result.findElement(By.xpath(selectorDescription)).getText()
                    )
            );
        }
        return this.collectionResults;
    }

    public boolean goToPage(String pageName) {
        WebElement pageLink = (WebElement) collectionResults.stream()
                .filter(x->x.get("NAME_PAGE").toString().contains(pageName))
                .findFirst()
                .get().get("WEB_ELEMENT");
        pageLink.findElement(By.xpath(selectorNamePage)).click();
        return true;
    }
}
