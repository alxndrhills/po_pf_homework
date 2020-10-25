package ru.bellintegrator.page;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;
import ru.bellintegrator.page.factory.GoogleSearchFactory;
import ru.bellintegrator.page.object.GoogleSearch;
import ru.bellintegrator.page.object.OpenBank;

import java.util.List;
import java.util.Map;

public class Tests extends TestBase {

    @Test
    public void findGladiolusLinkPO() {
        GoogleSearch googleSearch = new GoogleSearch(driver, "Гладиолус");
        List<Map<String, Object>> resultSearch = googleSearch.getCollectionResults();
        System.out.println(resultSearch);
    }

    @Test
    public void findGladiolusLinkPF() {
        GoogleSearchFactory googleSearchFactory = PageFactory.initElements(driver, GoogleSearchFactory.class);
        googleSearchFactory.findLinks("Гладиолус");
        Assertions.assertTrue(googleSearchFactory.getLinks().stream().anyMatch(x -> x.getText().contains("Шпажник — Википедия")),
                "Страница не найдена");
    }

    @Test void testExchangeRates() {
        GoogleSearch googleSearch = new GoogleSearch(driver, "Открытие");
        List<Map<String, Object>> resultSearch = googleSearch.getCollectionResults();
        googleSearch.goToPage("Банк «Открытие» — вклады, кредитные и дебетовые");

        OpenBank openBank = new OpenBank(driver);
        List<Map<String,String>> collectExchangeRates = openBank.getCollectExchangeRates();
        System.out.println(collectExchangeRates.size());
        System.out.println(collectExchangeRates);

        Assertions.assertTrue(
                Double.parseDouble(
                        collectExchangeRates.stream()
                                .filter(x->x.get("Валюта обмена").contains("USD"))
                                .findFirst()
                                .get().get("Банк покупает").replace(",","."))
                        <
                        Double.parseDouble(
                                collectExchangeRates.stream()
                                        .filter(x->x.get("Валюта обмена").contains("USD"))
                                        .findFirst()
                                        .get().get("Банк продает").replace(",","."))
        );
    }
}
