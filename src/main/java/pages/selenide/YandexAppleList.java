package pages.selenide;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class YandexAppleList extends BasePage {

    public LinkedHashMap<Integer, List<String>> allThePagesCatalog;
    public int currentPage = 0;
    //List<String> names = new ArrayList<>();

    @Step("Проверка, что мы в разделе 'Смартфоны'")
    public YandexAppleList doesPageContainsSmartphones() {
        Assertions.assertTrue(WebDriverRunner.url().contains("catalog--smartfony"));
        return this;
    }

    @Step("Настройка фильтра")
    public YandexAppleList setFilter() {
        $x("//span[../span[contains(text(), 'Apple')] and not(contains(text(), 'Apple'))]").click();
        return this;
    }

    @Step("Парсинг")
    public YandexAppleList  parsingCatalogs() {
        allThePagesCatalog = new LinkedHashMap<>();
        parsingAllThePages();
        return this;
    }

    public void parsingAllThePages() {
        currentPage = currentPage + 1;
        allThePagesCatalog.put(currentPage, new ArrayList<>());
        ElementsCollection elementsNames = $$x("//h3[@role='link']");
        $x("//div[@class = '_2q2DD']").scrollTo();
        int index = 1;
        for (SelenideElement sel:elementsNames ) {
            //String name = elementsNames.get(index).getText();
            String name = $x("(//h3[@role='link'])[" + index + "]").getText();
            allThePagesCatalog.get(currentPage).add(name);
        }
        if (!$$x("//span[contains(text(), 'Вперёд')]").shouldBe().isEmpty()) {
            $$x("//span[contains(text(), 'Вперёд')]").get(0).click();
            parsingAllThePages(); // Рекурсивный вызов метода
        }
    }

}
