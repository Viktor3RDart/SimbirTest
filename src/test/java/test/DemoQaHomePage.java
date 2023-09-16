package test;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;


public class DemoQaHomePage {

    private final WebDriver driver;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(css = "#genterWrapper > * div:nth-child(1) > label")
    private WebElement genderSelectMale;

    @FindBy(css = "#genterWrapper > * div:nth-child(2) > label")
    private WebElement genderSelectFemale;

    @FindBy(css = "#genterWrapper > * div:nth-child(3) > label")
    private WebElement genderSelectOther;

    @FindBy(id = "userNumber")
    private WebElement mobileField;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthField;

    @FindBy(css = ".react-datepicker__day--selected.react-datepicker__day--today")
    private WebElement dateOfBirthTodayDate;

    @FindBy(css = "#subjectsInput")
    private WebElement subjectsField;

    @FindBy(id = "uploadPicture")
    private WebElement pictureDownloadButton;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressField;

    @FindBy(id = "state")
    private WebElement selectStateButton;

    @FindBy(css = "#react-select-3-input")
    private WebElement selectStateField;

    @FindBy(id = "city")
    private WebElement selectCityButton;

    @FindBy(css = "#react-select-4-input")
    private WebElement selectCityField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement regModalWindowTitle;

    private String testDateOfBirth;

    public DemoQaHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Выбор элемента из списка")
    public void selectByName(WebElement elementToClick, WebElement elementToText, String text) {
        elementToClick.click();
        elementToText.sendKeys(text);
        elementToText.sendKeys(Keys.ENTER);
    }

    @Step("Получение выбранной даты из календаря")
    public String getTestData() {
        String date = dateOfBirthField.getAttribute("value");
        switch (date.substring(3, 7)) {
            case "Sep " -> date = date.replaceAll("Sep ", "September,");
            case "Oct " -> date = date.replaceAll("Oct ", "October,");
            case "Nov " -> date = date.replaceAll("Nov ", "November,");
            case "Dec " -> date = date.replaceAll("Dec ", "December,");
            case "Jan " -> date = date.replaceAll("Jan ", "January,");
            case "Feb " -> date = date.replaceAll("Feb ", "February,");
            case "Mar " -> date = date.replaceAll("Mar ", "March,");
            case "Apr " -> date = date.replaceAll("Apr ", "April,");
            case "May " -> date = date.replaceAll("May ", "May,");
            case "Jun " -> date = date.replaceAll("Jun ", "June,");
            case "Jul " -> date = date.replaceAll("Jul ", "July,");
            case "Aug " -> date = date.replaceAll("Aug ", "August,");
        }
        return date;
    }

    @Step("Выбрать пол на странице")
    public void selectGender() {
        switch (DemoQaHomePageTestData.TEST_GENDER) {
            case "Male" -> genderSelectMale.click();
            case "Female" -> genderSelectFemale.click();
            case "Other" -> genderSelectOther.click();
            default -> throw new InvalidArgumentException("Вы ввели не допустимое наименование гендера: "
                    + DemoQaHomePageTestData.TEST_GENDER + ", допустимые значения : Male, Female, Other");
        }
    }

    @Step("Прокрутка страницы в самый низ")
    public void scrollToDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 250);");
    }


    @Step("Получить все данные из модального окна регистрации")
    public Map<String, String> giveAllDataFromModalWin() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String xpathModalWinDataValue = "(//tr/td[2])";
            String xpathModalWinDataLabel = "(//tr/td[1])";
            map.put((driver.findElement(By.xpath((xpathModalWinDataLabel + "[" + (i + 1) + "]")))).getText(),
                    (driver.findElement(By.xpath((xpathModalWinDataValue + "[" + (i + 1) + "]")))).getText());
        }
        return map;
    }

    @Step("Заполнение всей формы регистрации")
    public void fullFillForm(WebDriverWait wait) {
        sendKeys(firstNameField, DemoQaHomePageTestData.TEST_FIRST_NAME);
        sendKeys(lastNameField, DemoQaHomePageTestData.TEST_LAST_NAME);
        sendKeys(emailField, DemoQaHomePageTestData.TEST_EMAIL);
        selectGender();
        sendKeys(mobileField, DemoQaHomePageTestData.TEST_MOBILE);
        clickElement(dateOfBirthField);
        clickElement(dateOfBirthTodayDate);
        setTestDateOfBirth(getTestData());
        sendKeys(subjectsField, DemoQaHomePageTestData.TEST_SUBJECTS);
        sendKeys(pictureDownloadButton, DemoQaHomePageTestData.pathForFile(DemoQaHomePageTestData.TEST_PICTURE));
        sendKeys(currentAddressField, DemoQaHomePageTestData.TEST_CURRENT_ADDRESS);
        scrollToDown();
        selectByName(selectStateButton, selectStateField, DemoQaHomePageTestData.TEST_STATE);
        selectByName(selectCityButton, selectCityField, DemoQaHomePageTestData.TEST_CITY);
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        clickElement(submitButton);
    }

    @Step("Проверка соответствия введенного элемента {expected} и полученного {actual}")
    public void checkField(String actual, String expected) {
        Assert.assertEquals(expected, actual);
    }

    @Step("Ввод в поле значения {writeInElement}")
    public void sendKeys(WebElement element, String writeInElement) {
        element.sendKeys(writeInElement);
    }

    @Step("Клик по элементу {element}")
    public void clickElement(WebElement element) {
        element.click();
    }

    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getMobileField() {
        return mobileField;
    }

    public WebElement getDateOfBirthField() {
        return dateOfBirthField;
    }

    public WebElement getDateOfBirthTodayDate() {
        return dateOfBirthTodayDate;
    }

    public WebElement getSubjectsField() {
        return subjectsField;
    }

    public WebElement getPictureDownloadButton() {
        return pictureDownloadButton;
    }

    public WebElement getCurrentAddressField() {
        return currentAddressField;
    }

    public WebElement getSelectStateButton() {
        return selectStateButton;
    }

    public WebElement getSelectStateField() {
        return selectStateField;
    }

    public WebElement getSelectCityButton() {
        return selectCityButton;
    }

    public WebElement getSelectCityField() {
        return selectCityField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getRegModalWindowTitle() {
        return regModalWindowTitle;
    }

    public String getTestDateOfBirth() {
        return testDateOfBirth;
    }

    public void setFirstNameField(WebElement firstNameField) {
        this.firstNameField = firstNameField;
    }

    public void setLastNameField(WebElement lastNameField) {
        this.lastNameField = lastNameField;
    }

    public void setEmailField(WebElement emailField) {
        this.emailField = emailField;
    }

    public void setGenderSelectMale(WebElement genderSelectMale) {
        this.genderSelectMale = genderSelectMale;
    }

    public void setGenderSelectFemale(WebElement genderSelectFemale) {
        this.genderSelectFemale = genderSelectFemale;
    }

    public void setGenderSelectOther(WebElement genderSelectOther) {
        this.genderSelectOther = genderSelectOther;
    }

    public void setMobileField(WebElement mobileField) {
        this.mobileField = mobileField;
    }

    public void setDateOfBirthField(WebElement dateOfBirthField) {
        this.dateOfBirthField = dateOfBirthField;
    }

    public void setDateOfBirthTodayDate(WebElement dateOfBirthTodayDate) {
        this.dateOfBirthTodayDate = dateOfBirthTodayDate;
    }

    public void setSubjectsField(WebElement subjectsField) {
        this.subjectsField = subjectsField;
    }

    public void setPictureDownloadButton(WebElement pictureDownloadButton) {
        this.pictureDownloadButton = pictureDownloadButton;
    }

    public void setCurrentAddressField(WebElement currentAddressField) {
        this.currentAddressField = currentAddressField;
    }

    public void setSelectStateButton(WebElement selectStateButton) {
        this.selectStateButton = selectStateButton;
    }

    public void setSelectStateField(WebElement selectStateField) {
        this.selectStateField = selectStateField;
    }

    public void setSelectCityButton(WebElement selectCityButton) {
        this.selectCityButton = selectCityButton;
    }

    public void setSelectCityField(WebElement selectCityField) {
        this.selectCityField = selectCityField;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }

    public void setRegModalWindowTitle(WebElement regModalWindowTitle) {
        this.regModalWindowTitle = regModalWindowTitle;
    }

    public void setTestDateOfBirth(String testDateOfBirth) {
        this.testDateOfBirth = testDateOfBirth;
    }
}