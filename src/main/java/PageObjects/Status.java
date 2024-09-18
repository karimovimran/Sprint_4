package PageObjects;

import Config_Driver.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Status {
    private final WebDriver driver;

    // локатор картинки с текстом "Заказ не найден"
    private final By notFound = By.cssSelector("[alt='Not found']");

    public Status(WebDriver driver) {
        this.driver = driver;
    }


    public void checkNotFound() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(notFound));
    }

}