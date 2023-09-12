package DemoQa;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DemoQaHomePage {

    WebDriver driver;

    public DemoQaHomePageTestData testData;

    @FindBy(id = "firstName")
    public
    WebElement FirstNameField;

    @FindBy(id = "lastName")
    public
    WebElement LastNameField;

    @FindBy(id = "userEmail")
    public
    WebElement EmailField;

    @FindBy(css = "#genterWrapper > * div:nth-child(1) > label")
    public
    WebElement GenderSelectMale;

    @FindBy(id = "#genterWrapper > * div:nth-child(2) > label")
    public
    WebElement GenderSelectFemale;

    @FindBy(id = "#genterWrapper > * div:nth-child(3) > label")
    public
    WebElement GenderSelectOther;

    @FindBy(id = "userNumber")
    public
    WebElement MobileField;

    @FindBy(id = "dateOfBirthInput")
    public
    WebElement DateOfBirthField;

    @FindBy(css = "#subjectsInput")
    public
    WebElement SubjectsField;

    @FindBy(id = "uploadPicture")
    public
    WebElement PictureDownloadButton;

    @FindBy(id = "currentAddress")
    public
    WebElement CurrentAddressField;

    @FindBy(id = "state")
    public
    WebElement SelectStateButton;

    @FindBy(css = "#react-select-3-input")
    public
    WebElement SelectStateField;

    @FindBy(id = "city")
    public
    WebElement SelectCityButton;

    @FindBy(css = "#react-select-4-input")
    public
    WebElement SelectCityField;

    @FindBy(id = "submit")
    public
    WebElement SubmitButton;

    @FindBy(css = "")
    public
    WebElement RegModalWindow;

    @FindBy(css = "")
    public
    WebElement RegModalWindowTitle;

    public DemoQaHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void SelectByName(WebElement elementToClick, WebElement elementToText, String text) {
        elementToClick.click();
        elementToText.sendKeys(text);
        elementToText.sendKeys(Keys.ENTER);
    }

    public void scrollToDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 250);");
    }

    public void FullFillForm(WebDriverWait wait) {
        testData = new DemoQaHomePageTestData();

        FirstNameField.sendKeys(testData.testFirstName);
        LastNameField.sendKeys(testData.testLastName);
        EmailField.sendKeys(testData.testEmail);
        GenderSelectMale.click();
        MobileField.sendKeys(testData.testMobile);
        DateOfBirthField.clear();
        DateOfBirthField.sendKeys(testData.testDateOfBirth);
        //   Thread.sleep(20000);
        SubjectsField.sendKeys(testData.testSubjects);
        PictureDownloadButton.sendKeys(testData.testPicture);
        CurrentAddressField.sendKeys(testData.testCurrentAddress);
        scrollToDown();
        SelectByName(SelectStateButton, SelectStateField, testData.testState);
        SelectByName(SelectCityButton, SelectCityField, testData.testCity);
        wait.until(ExpectedConditions.visibilityOf(SubmitButton));
        SubmitButton.click();

        //Thread.sleep(20000);

    }

}
