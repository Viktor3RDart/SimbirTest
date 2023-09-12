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
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void PageTest() throws InterruptedException {
        homePage = new DemoQaHomePage(driver);
        testData = new DemoQaHomePageTestData();

        homePage.FirstNameField.sendKeys(testData.testFirstName);
        homePage.LastNameField.sendKeys(testData.testLastName);
        homePage.EmailField.sendKeys(testData.testEmail);
        homePage.GenderSelectMale.click();
        homePage.MobileField.sendKeys(testData.testMobile);
        homePage.DateOfBirthField.clear();
        homePage.DateOfBirthField.sendKeys(testData.testDateOfBirth);
        Thread.sleep(20000);
        homePage.SubjectsField.sendKeys(testData.testSubjects);
        homePage.PictureDownloadButton.sendKeys(testData.testPicture);
        homePage.CurrentAddressField.sendKeys(testData.testSubjects);
        homePage.scrollToDown();
        homePage.SelectByName(homePage.SelectStateButton, homePage.SelectStateField, testData.testState);
        homePage.SelectByName(homePage.SelectCityButton, homePage.SelectCityField, testData.testCity);
        wait.until(ExpectedConditions.visibilityOf(homePage.SubmitButton));
        homePage.SubmitButton.click();

        Thread.sleep(20000);



    }

    @Test
    public void PageTestShort() {
        homePage = new DemoQaHomePage(driver);
        homePage.FullFillForm(this.wait);
    }



    @After
    public void tearDown() {
        driver.quit();
    }
}
