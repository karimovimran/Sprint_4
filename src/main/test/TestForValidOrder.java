import Config_Driver.DriverWeb;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import PageObjects.AboutOrder;
import PageObjects.MainPage;
import PageObjects.UserData;
import Config_Driver.Config;

// Класс ValidOrderTest выполняет параметризованные тесты для создания действительных заказов
@RunWith(Parameterized.class)
public class TestForValidOrder {

    // Поля для хранения данных, необходимых для создания заказа
    private final String name;
    private final String surName;
    private final String address;
    private final int idMetro;
    private final String telephone;
    private final String date;
    private final String period;
    private final String comment;
    private final String color;

    // Конструктор класса, который инициализирует поля
    public TestForValidOrder(String name, String surName, String address, int idStation, String telephone, String date, String period, String color, String comment) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.idMetro = idStation;
        this.telephone = telephone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }


    @Rule
    public DriverWeb driverWeb = new DriverWeb();

    // Параметризированный метод, предоставляющий данные для теста. Возвращает двумерный массив с данными заказа
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Никита", "Ягодников", "Порфирьевич", 1, "77732145688",  "21.09.2024", "сутки", "black", "Оставить у подъезда"},
                {"Серафим", "Голышев", "Всеволодович", 5, "7997456233", "10.06.2024", "две недели", "black", "Оставить у замка."},
                {"Зоя", "Проничева", "Васильевна", 15, "77786543842", "15.08.2024", "семеро суток", "grey", "У главного входа."},
        };
    }
    // Тест для создания заказа через кнопку в верху страницы
    @Test
    public void createValidOrderByUpButton() {
        WebDriver driver = driverWeb.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(Config.COOKIE_NAME_1, Config.COOKIE_VALUE)
                .addCookie(Config.COOKIE_NAME_2, Config.COOKIE_NAME_2)
                .open();

        // Заполнение данных заказа и проверка его создания
        UserData userInformation = mainPage.clickOnOrderButtonUpHeader();
        userInformation.enterUserName(name)
                .enterUserSurName(surName)
                .enterUserAddress(address)
                .selectMetro(idMetro)
                .enterUserPhone(telephone);

        AboutOrder aboutOrder = userInformation.clickOnButtonNext();
        aboutOrder.enterDate(date)
                .selectOrderPeriod(period)
                .selectColor(color)
                .enterComment(comment)
                .clickToContinueOrder()
                .confirmOrder()
                .checkTheOrderCreation();
    }

    // Тест для создания действительного заказа в внизу
    @Test
    public void createValidOrderByDownButton() {
        WebDriver driver = driverWeb.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(Config.COOKIE_NAME_1, Config.COOKIE_VALUE)
                .addCookie(Config.COOKIE_NAME_2, Config.COOKIE_NAME_2)
                .open();

        // Заполнение данных заказа и проверка его создания
        UserData userInformation = mainPage.clickOnDownOrderButton();
        userInformation.enterUserName(name)
                .enterUserSurName(surName)
                .enterUserAddress(address)
                .selectMetro(idMetro)
                .enterUserPhone(telephone);

        AboutOrder aboutOrder = userInformation.clickOnButtonNext(); // Переход к следующему этапу заказа
        aboutOrder.enterDate(date)
                .selectOrderPeriod(period)
                .selectColor(color)
                .enterComment(comment)
                .clickToContinueOrder()
                .confirmOrder()
                .checkTheOrderCreation();
    }
}
