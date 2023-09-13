package DemoQa;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class DemoQaHomePage {

    WebDriver driver;

    public DemoQaHomePageTestData testData;

    public String testDateOfBirth;

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

    @FindBy(css = "#genterWrapper > * div:nth-child(2) > label")
    public
    WebElement GenderSelectFemale;

    @FindBy(css = "#genterWrapper > * div:nth-child(3) > label")
    public
    WebElement GenderSelectOther;

    @FindBy(id = "userNumber")
    public
    WebElement MobileField;

    @FindBy(id = "dateOfBirthInput")
    public
    WebElement DateOfBirthField;

    @FindBy(css = "div.react-datepicker__day.react-datepicker__day--013.react-datepicker__day--selected.react" +
            "-datepicker__day--today")
    public
    WebElement DateOfBirthTodayDate;

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

    @FindBy(id = "example-modal-sizes-title-lg")
    public
    WebElement RegModalWindowTitle;

    String XpathModalWinDataValue = "(//tr/td[2])";

    String XpathModalWinDataLabel = "(//tr/td[1])";

    public DemoQaHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectByName(WebElement elementToClick, WebElement elementToText, String text) {
        elementToClick.click();
        elementToText.sendKeys(text);
        elementToText.sendKeys(Keys.ENTER);
    }

    public String getTestData() {
        String Data = DateOfBirthField.getAttribute("value");
        switch (Data.substring(3, 7)) {
            case "Sep " -> Data = Data.replaceAll("Sep ", "September,");
            case "Oct " -> Data = Data.replaceAll("Oct ", "October,");
            case "Nov " -> Data = Data.replaceAll("Nov ", "November,");
            case "Dec " -> Data = Data.replaceAll("Dec ", "December,");
            case "Jan " -> Data = Data.replaceAll("Jan ", "January,");
            case "Feb " -> Data = Data.replaceAll("Feb ", "February,");
            case "Mar " -> Data = Data.replaceAll("Mar ", "March,");
            case "Apr " -> Data = Data.replaceAll("Apr ", "April,");
            case "May " -> Data = Data.replaceAll("May ", "May,");
            case "Jun " -> Data = Data.replaceAll("Jun ", "June,");
            case "Jul " -> Data = Data.replaceAll("Jul ", "July,");
            case "Aug " -> Data = Data.replaceAll("Aug ", "August,");
        }
        return Data;
    }

    public void SelectGender() {
        testData = new DemoQaHomePageTestData();
        switch (testData.testGender) {
            case "Male" -> GenderSelectMale.click();
            case "Female" -> GenderSelectFemale.click();
            case "Other" -> GenderSelectOther.click();
            default -> throw new InvalidArgumentException("Вы ввели не допустимое наименование гендера: "
                    + testData.testGender + ", допустимые значения : Male, Female, Other");
        }
    }

    public void scrollToDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 250);");
    }

    public String pathForFile(String fileName) {
        return new File("./" + fileName).getAbsolutePath();
    }

    public Map<String, String> GiveAllDataFromModalWin() {
        Map<String, String> Map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Map.put((driver.findElement(By.xpath((XpathModalWinDataLabel + "[" + (i + 1) + "]")))).getText(),
                    (driver.findElement(By.xpath((XpathModalWinDataValue + "[" + (i + 1) + "]")))).getText());
        }
        return Map;
    }

    public void fullFillForm(WebDriverWait wait) {
        testData = new DemoQaHomePageTestData();
        FirstNameField.sendKeys(testData.testFirstName);
        LastNameField.sendKeys(testData.testLastName);
        EmailField.sendKeys(testData.testEmail);
        SelectGender();
        MobileField.sendKeys(testData.testMobile);
        DateOfBirthField.click();
        DateOfBirthTodayDate.click();
        testDateOfBirth = getTestData();
        SubjectsField.sendKeys(testData.testSubjects);
        PictureDownloadButton.sendKeys(pathForFile(testData.testPicture));
        CurrentAddressField.sendKeys(testData.testCurrentAddress);
        scrollToDown();
        selectByName(SelectStateButton, SelectStateField, testData.testState);
        selectByName(SelectCityButton, SelectCityField, testData.testCity);
        wait.until(ExpectedConditions.visibilityOf(SubmitButton));
        SubmitButton.click();
    }

}
