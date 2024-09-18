import Config_Driver.DriverWeb;
import Config_Driver.Config;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import PageObjects.MainPage;
import PageObjects.Yandex;


public class LogoClickTest {
    // Текст, который будет вводиться на странице Yandex
    String text = "Hi!";


    @Rule
    public DriverWeb driverWeb = new DriverWeb();

    // Тест для проверки перенаправления на страницу Yandex по клику на логотип
    @Test
    public void openYandex() {
        WebDriver driver = this.driverWeb.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(Config.COOKIE_NAME_1, Config.COOKIE_VALUE)
                .addCookie(Config.COOKIE_NAME_2, Config.COOKIE_NAME_2)
                .open();


        Yandex ya = mainPage.clickOnYandex();

        ya.openInNewTab()
                .enterText(text);
    }

    // Тест для проверки перенаправления на страницу Samokat по клику на логотип
    @Test
    public void openSamokatPage() {
        WebDriver driver = this.driverWeb.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(Config.COOKIE_NAME_1, Config.COOKIE_VALUE)
                .addCookie(Config.COOKIE_NAME_2, Config.COOKIE_NAME_2)
                .open()
                .clickOnSamokat()
                .checkRedirectToSamokat();
    }
}

