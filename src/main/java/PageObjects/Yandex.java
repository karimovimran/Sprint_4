package PageObjects;

import Config_Driver.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Yandex {
    private final WebDriver driver;

    // локатор в котором находится поле ввода
    private final By inputText = By.cssSelector("iframe[name]");

    // локатор поля поиска информации
    private final By inputSearch = By.name("text");

    public Yandex(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(String text) {
        driver.switchTo().frame(driver.findElement(inputText));

        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(inputSearch));

        driver.findElement(inputSearch).sendKeys(text);
    }

    public Yandex openInNewTab() {
        // создан список с вкладками браузера
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        // переключаюсь на вторую вкладку
        driver.switchTo().window(browserTabs.get(1));

        return this;
    }
}