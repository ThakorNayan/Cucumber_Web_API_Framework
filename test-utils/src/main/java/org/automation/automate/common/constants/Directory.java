package org.automation.automate.common.constants;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Directory {
    private Directory() {
        throw new IllegalStateException("Constant class");
    }

    public static final Path PARENT_PATH = FileSystems.getDefault().getPath(".").toAbsolutePath().getParent().getParent();
    public static final String TEST_UTIL_PATH = PARENT_PATH + "/test-utils/";
    public static final String UI_TESTS_PATH = PARENT_PATH + "/ui-tests/";
    public static final String API_TESTS_PATH = PARENT_PATH + "/api-tests/";
    public static final String SRC_MAIN_JAVA = TEST_UTIL_PATH + "src/main/java/";
    public static final String SRC_MAIN_RESOURCES = TEST_UTIL_PATH + "src/main/resources/";
    public static final String UI_TESTS_SRC_MAIN_RESOURCES = UI_TESTS_PATH + "src/main/resources/";
    public static final String UI_TESTS_SRC_TEST_RESOURCES = UI_TESTS_PATH + "src/test/resources/";
    public static final String API_TESTS_SRC_MAIN_RESOURCES = API_TESTS_PATH + "src/main/resources/";
    public static final String API_TESTS_SRC_TEST_RESOURCES = API_TESTS_PATH + "src/test/resources/";
    public static final String UI_TESTS_TESTDATA_PDF_FILES = UI_TESTS_SRC_TEST_RESOURCES + "testData/pdfFiles/";
    public static final String SRC_TEST_JAVA = TEST_UTIL_PATH + "src/test/java/";
    public static final String SRC_TEST_RESOURCES = TEST_UTIL_PATH + "src/test/resources/";
    public static final String ORG_AUTOMATION_AUTOMATE = "org/automation/automate/";
    public static final String MAIN_ORG_AUTOMATION_AUTOMATE = SRC_MAIN_JAVA + ORG_AUTOMATION_AUTOMATE;
    public static final String TEST_ORG_AUTOMATION_AUTOMATE = SRC_TEST_JAVA + ORG_AUTOMATION_AUTOMATE;
    public static final String CONFIG = SRC_MAIN_RESOURCES + "config/";
    public static final String UI_TESTS_CONFIG = UI_TESTS_SRC_MAIN_RESOURCES + "config/";
    public static final String DRIVERS = SRC_MAIN_RESOURCES + "drivers/";
    public static final String CHROME_DRIVER = DRIVERS + "chromedriver/chromedriver.exe";
    public static final String EDGE_DRIVER = DRIVERS + "edgedriver/msedgedriver.exe";
    public static final String GECKO_DRIVER = DRIVERS + "geckodriver/geckodriver.exe";

}
