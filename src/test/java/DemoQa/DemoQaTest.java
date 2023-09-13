package DemoQa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DemoQaTest {

    private WebDriver driver;
    private WebDriverWait wait;
    DemoQaHomePage homePage;
    DemoQaHomePageTestData testData;

    @Before
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
    public void PageTest() {
        homePage = new DemoQaHomePage(driver);
        testData = new DemoQaHomePageTestData();

        homePage.FirstNameField.sendKeys(testData.testFirstName);
        homePage.LastNameField.sendKeys(testData.testLastName);
        homePage.EmailField.sendKeys(testData.testEmail);
        homePage.SelectGender();
        homePage.MobileField.sendKeys(testData.testMobile);
        homePage.DateOfBirthField.click();
        homePage.DateOfBirthTodayDate.click();
        testData.testDateOfBirth = homePage.getTestData();
        homePage.SubjectsField.sendKeys(testData.testSubjects);
        homePage.PictureDownloadButton.sendKeys(homePage.pathForFile(testData.testPicture));
        homePage.CurrentAddressField.sendKeys(testData.testCurrentAddress);
        homePage.scrollToDown();
        homePage.selectByName(homePage.SelectStateButton, homePage.SelectStateField, testData.testState);
        homePage.selectByName(homePage.SelectCityButton, homePage.SelectCityField, testData.testCity);
        wait.until(ExpectedConditions.visibilityOf(homePage.SubmitButton));
        homePage.SubmitButton.click();
        assertAll("Проверка соответствия данных введенных в форму и полученных данных в модальном окне",
                () -> assertEquals(homePage.RegModalWindowTitle.getText(), testData.testModalWindowLogo),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Student Name"),
                        testData.testFirstName + " " + testData.testLastName),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Student Email"),
                        testData.testEmail),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Gender"),
                        testData.testGender),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Mobile"),
                        testData.testMobile),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Date of Birth"),
                        testData.testDateOfBirth),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Subjects"),
                        testData.testSubjects),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Picture"),
                        testData.testPicture),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Address"),
                        testData.testCurrentAddress),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("State and City"),
                        testData.testState + " " + testData.testCity));
    }

    @Test
    public void PageTestShort() {
        homePage = new DemoQaHomePage(driver);
        testData = new DemoQaHomePageTestData();
        homePage.fullFillForm(this.wait);
        assertAll("Проверка соответствия данных введенных в форму и полученных данных в модальном окне",
                () -> assertEquals(homePage.RegModalWindowTitle.getText(), testData.testModalWindowLogo),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Student Name"),
                        testData.testFirstName + " " + testData.testLastName),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Student Email"),
                        testData.testEmail),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Gender"),
                        testData.testGender),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Mobile"),
                        testData.testMobile),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Date of Birth"),
                        homePage.testDateOfBirth),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Subjects"),
                        testData.testSubjects),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Picture"),
                        testData.testPicture),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("Address"),
                        testData.testCurrentAddress),
                () -> assertEquals(homePage.GiveAllDataFromModalWin().get("State and City"),
                        testData.testState + " " + testData.testCity));
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
