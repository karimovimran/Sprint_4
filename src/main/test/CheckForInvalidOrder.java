import Config_Driver.DriverWeb;
import Config_Driver.Config;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import PageObjects.MainPage;

// Класс InvalidOrderCheck выполняет параметризованный тест для проверки недействительных номеров заказов
@RunWith(Parameterized.class)
public class CheckForInvalidOrder {
    private final String orderNumber;

    // Конструктор класса, который принимает номер заказа и присваивает его переменной orderNumber
    public CheckForInvalidOrder(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    @Rule
    public DriverWeb driverWebRule = new DriverWeb();


    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"123"}
        };
    }

    // Основной тест, который проверяет недействительность номера заказа
    @Test
    public void validateInvalidOrder() {
        WebDriver driver = driverWebRule.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(Config.COOKIE_NAME_1, Config.COOKIE_VALUE)
                .addCookie(Config.COOKIE_NAME_2, Config.COOKIE_NAME_2)
                .open()
                .clickOnStatusButton()
                .enterOrderNumber(orderNumber)
                .clickOnGo()
                .checkNotFound();
    }
}