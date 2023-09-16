package test;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;


@Epic("Тестовое задание")
@Feature("Проверка соответствия")
public class DemoQaTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    @Step("Подготовка старта драйвера, переход на страницу")
    public void setUp() {
        ChromeOptions opt = new ChromeOptions();
        opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(opt);
        driver.manage().window().setSize(new Dimension(1500, 1200));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    @DisplayName("Тест по тестовому заданию, тест проверяет что заполненные данные соответствуют полученным" +
            " в модальном окне")
    @Description(value = "Ожидаемый результат:\n" +
            "1. Появилось всплывающее окно с заголовком Thanks for submitting the form\n" +
            "2. В таблице на окне отображаются введенные ранее значения")
    @Severity(CRITICAL)
    public void checkModalWindow_inputData_modalWindowFullFill_1() {
        DemoQaHomePage homePage = new DemoQaHomePage(driver);

        homePage.sendKeys(homePage.getFirstNameField(), DemoQaHomePageTestData.TEST_FIRST_NAME);
        homePage.sendKeys(homePage.getLastNameField(), DemoQaHomePageTestData.TEST_LAST_NAME);
        homePage.sendKeys(homePage.getEmailField(), DemoQaHomePageTestData.TEST_EMAIL);
        homePage.selectGender();
        homePage.sendKeys(homePage.getMobileField(), DemoQaHomePageTestData.TEST_MOBILE);
        homePage.clickElement(homePage.getDateOfBirthField());
        homePage.clickElement(homePage.getDateOfBirthTodayDate());
        homePage.setTestDateOfBirth(homePage.getTestData());
        homePage.sendKeys(homePage.getSubjectsField(), DemoQaHomePageTestData.TEST_SUBJECTS);
        homePage.sendKeys(homePage.getPictureDownloadButton(),
                DemoQaHomePageTestData.pathForFile(DemoQaHomePageTestData.TEST_PICTURE));
        homePage.sendKeys(homePage.getCurrentAddressField(), DemoQaHomePageTestData.TEST_CURRENT_ADDRESS);
        homePage.scrollToDown();
        homePage.selectByName(homePage.getSelectStateButton(), homePage.getSelectStateField(),
                DemoQaHomePageTestData.TEST_STATE);
        homePage.selectByName(homePage.getSelectCityButton(), homePage.getSelectCityField(),
                DemoQaHomePageTestData.TEST_CITY);
        wait.until(ExpectedConditions.visibilityOf(homePage.getSubmitButton()));
        homePage.clickElement(homePage.getSubmitButton());
        Map<String, String> giveData = homePage.giveAllDataFromModalWin();
        assertAll("Проверка соответствия данных введенных в форму и полученных данных в модальном окне",
                () -> homePage.checkField(homePage.getRegModalWindowTitle().getText(),
                        DemoQaHomePageTestData.TEST_MODAL_WINDOW_LOGO),
                () -> homePage.checkField(homePage.giveAllDataFromModalWin().get("Student Name"),
                        DemoQaHomePageTestData.TEST_FIRST_NAME
                                + " " + DemoQaHomePageTestData.TEST_LAST_NAME),
                () -> homePage.checkField(giveData.get("Student Email"), DemoQaHomePageTestData.TEST_EMAIL),
                () -> homePage.checkField(giveData.get("Student Email"), DemoQaHomePageTestData.TEST_EMAIL),
                () -> homePage.checkField(giveData.get("Gender"), DemoQaHomePageTestData.TEST_GENDER),
                () -> homePage.checkField(giveData.get("Mobile"), DemoQaHomePageTestData.TEST_MOBILE),
                () -> homePage.checkField(giveData.get("Date of Birth"), homePage.getTestDateOfBirth()),
                () -> homePage.checkField(giveData.get("Subjects"), DemoQaHomePageTestData.TEST_SUBJECTS),
                () -> homePage.checkField(giveData.get("Picture"), DemoQaHomePageTestData.TEST_PICTURE),
                () -> homePage.checkField(giveData.get("Address"), DemoQaHomePageTestData.TEST_CURRENT_ADDRESS),
                () -> homePage.checkField(giveData.get("State and City"), DemoQaHomePageTestData.TEST_STATE
                        + " " + DemoQaHomePageTestData.TEST_CITY));
    }

    @Test
    @DisplayName("Тест, с использованием fullFillForm из DemoQaHomePage, по тестовому заданию, тест проверяет" +
            " что заполненные данные соответствуют полученным в модальном окне")
    @Description(value = "Ожидаемый результат:\n" +
            "1. Появилось всплывающее окно с заголовком Thanks for submitting the form\n" +
            "2. В таблице на окне отображаются введенные ранее значения")
    @Severity(CRITICAL)
    public void checkModalWindow_inputData_modalWindowFullFill_2() {
        DemoQaHomePage homePage = new DemoQaHomePage(driver);

        homePage.fullFillForm(wait);
        Map<String, String> giveData = homePage.giveAllDataFromModalWin();
        assertAll("Проверка соответствия данных введенных в форму и полученных данных в модальном окне",
                () -> homePage.checkField(homePage.getRegModalWindowTitle().getText(),
                        DemoQaHomePageTestData.TEST_MODAL_WINDOW_LOGO),
                () -> homePage.checkField(homePage.giveAllDataFromModalWin().get("Student Name"),
                        DemoQaHomePageTestData.TEST_FIRST_NAME
                                + " " + DemoQaHomePageTestData.TEST_LAST_NAME),
                () -> homePage.checkField(giveData.get("Student Email"), DemoQaHomePageTestData.TEST_EMAIL),
                () -> homePage.checkField(giveData.get("Student Email"), DemoQaHomePageTestData.TEST_EMAIL),
                () -> homePage.checkField(giveData.get("Gender"), DemoQaHomePageTestData.TEST_GENDER),
                () -> homePage.checkField(giveData.get("Mobile"), DemoQaHomePageTestData.TEST_MOBILE),
                () -> homePage.checkField(giveData.get("Date of Birth"), homePage.getTestDateOfBirth()),
                () -> homePage.checkField(giveData.get("Subjects"), DemoQaHomePageTestData.TEST_SUBJECTS),
                () -> homePage.checkField(giveData.get("Picture"), DemoQaHomePageTestData.TEST_PICTURE),
                () -> homePage.checkField(giveData.get("Address"), DemoQaHomePageTestData.TEST_CURRENT_ADDRESS),
                () -> homePage.checkField(giveData.get("State and City"), DemoQaHomePageTestData.TEST_STATE
                        + " " + DemoQaHomePageTestData.TEST_CITY));
    }

    @AfterEach
    @Step("Закрыть драйвер")
    public void tearDown() {
        driver.quit();
    }
}
