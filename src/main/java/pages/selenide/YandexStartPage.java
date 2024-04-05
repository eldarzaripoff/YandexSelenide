package pages.selenide;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.codeborne.selenide.Selenide.*;


public class YandexStartPage extends BasePage {
    @Step("Переходим на страницу с {name}")
    public <T extends BasePage> T goToCatalog(String name, Class<T> typeNextPage) {
        $x("//button[@class='_30-fz button-focus-ring Hkr1q _1pHod _2rdh3 _3rbM-']").shouldBe(Condition.visible).click();
        $x("//div[@data-zone-name='catalog-content']//span[contains(text(), 'Электроника')]").hover();
        $x("//a[text() = '" + name + "']").click();
        try {
            Constructor<?> constructor = typeNextPage.getDeclaredConstructor();
            return (T) constructor.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException("Error creating instance of " + typeNextPage.getName(), e);
    }
    }}
