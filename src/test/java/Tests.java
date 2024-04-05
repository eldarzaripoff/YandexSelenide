import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.selenide.YandexAppleList;
import pages.selenide.YandexStartPage;

public class Tests {
    @Feature("Проверка каталога на соответствие фильтру")
    @DisplayName("Проверка раздела каталога на соответствие Apple")
    @Test
    public void isYandexContainsApple() {
        Selenide.open("https://market.yandex.ru/", YandexStartPage.class).
                goToCatalog("Смартфоны", YandexAppleList.class).
                doesPageContainsSmartphones().
                setFilter().
                parsingCatalogs();
    }









}
