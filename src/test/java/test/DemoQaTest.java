package test;

import io.qameta.allure.Description;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@Epic("Тестовое задание")
@Feature("Проверка соответствия")
public class DemoQaTest {

    private WebDriver driver;
    private WebDriverWait wait;
    DemoQaHomePage homePage;
    DemoQaHomePageTestData testData;

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
    public void PageTest_1() {
        homePage = new DemoQaHomePage(driver);
        testData = new DemoQaHomePageTestData();

        homePage.fullField(homePage.FirstNameField, testData.testFirstName);
        homePage.fullField(homePage.LastNameField, testData.testLastName);
        homePage.fullField(homePage.EmailField, testData.testEmail);
        homePage.SelectGender();
        homePage.fullField(homePage.MobileField, testData.testMobile);
        homePage.elementClick(homePage.DateOfBirthField);
        homePage.elementClick(homePage.DateOfBirthTodayDate);
        testData.testDateOfBirth = homePage.getTestData();
        homePage.fullField(homePage.SubjectsField, testData.testSubjects);
        homePage.fullField(homePage.PictureDownloadButton, homePage.pathForFile(testData.testPicture));
        homePage.fullField(homePage.CurrentAddressField, testData.testCurrentAddress);
        homePage.scrollToDown();
        homePage.selectByName(homePage.SelectStateButton, homePage.SelectStateField, testData.testState);
        homePage.selectByName(homePage.SelectCityButton, homePage.SelectCityField, testData.testCity);
        wait.until(ExpectedConditions.visibilityOf(homePage.SubmitButton));
        homePage.elementClick(homePage.SubmitButton);
        assertAll("Проверка соответствия данных введенных в форму и полученных данных в модальном окне",
                () -> homePage.checkField(homePage.RegModalWindowTitle.getText(), testData.testModalWindowLogo),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Student Name"), testData.testFirstName
                        + " " + testData.testLastName),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Student Email"), testData.testEmail),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Student Email"), testData.testEmail),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Gender"), testData.testGender),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Mobile"), testData.testMobile),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Date of Birth"), testData.testDateOfBirth),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Subjects"), testData.testSubjects),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Picture"), testData.testPicture),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Address"), testData.testCurrentAddress),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("State and City"), testData.testState
                        + " " + testData.testCity));
    }

    @Test
    @DisplayName("Тест, с использованием всех методов из DemoQaHomePage, по тестовому заданию, тест проверяет" +
            " что заполненные данные соответствуют полученным в модальном окне")
    @Description(value = "Ожидаемый результат:\n" +
            "1. Появилось всплывающее окно с заголовком Thanks for submitting the form\n" +
            "2. В таблице на окне отображаются введенные ранее значения")
    public void PageTestShort_2() {
        homePage = new DemoQaHomePage(driver);
        testData = new DemoQaHomePageTestData();
        homePage.fullFillForm(wait);
        assertAll("Проверка соответствия данных введенных в форму и полученных данных в модальном окне",
                () -> homePage.checkField(homePage.RegModalWindowTitle.getText(), testData.testModalWindowLogo),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Student Name"), testData.testFirstName
                        + " " + testData.testLastName),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Student Email"), testData.testEmail),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Student Email"), testData.testEmail),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Gender"), testData.testGender),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Mobile"), testData.testMobile),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Date of Birth"), homePage.testDateOfBirth),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Subjects"), testData.testSubjects),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Picture"), testData.testPicture),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("Address"), testData.testCurrentAddress),
                () -> homePage.checkField(homePage.GiveAllDataFromModalWin().get("State and City"), testData.testState
                        + " " + testData.testCity));
    }

    @AfterEach
    @Step("Закрыть драйвер")
    public void tearDown() {
        driver.quit();
    }
}
