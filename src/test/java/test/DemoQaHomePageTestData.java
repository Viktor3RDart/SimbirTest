package test;

import io.qameta.allure.Step;

import java.io.File;

public class DemoQaHomePageTestData {

    public static final String TEST_LAST_NAME = "Test";

    public static final String TEST_FIRST_NAME = "Viktor";

    public static final String TEST_EMAIL = "test@email.ru";

    public static final String TEST_MOBILE = "0987654321";

    public static final String TEST_PICTURE = "test.jpg";

    public static final String TEST_SUBJECTS = "TestForTest";

    public static final String TEST_CURRENT_ADDRESS = "Address TEST";

    public static final String TEST_STATE = "NCR";

    public static final String TEST_CITY = "Noida";

    public static final String TEST_MODAL_WINDOW_LOGO = "Thanks for submitting the form";

    public static final String TEST_GENDER = "Female";

    @Step("Составление абсолютного пути для файла {fileName}")
    public static String pathForFile(String fileName) {
        return new File("./" + fileName).getAbsolutePath();
    }
}
