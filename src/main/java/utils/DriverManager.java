package utils;

import com.codeborne.selenide.Configuration;

public class DriverManager {

    private static final boolean DEFAULT_HEADLESS_MODE = false;
    private static final String DEFAULT_BROWSER_SIZE = "1920x1080";
    private static final long DEFAULT_TIMEOUT = 5000;

    private DriverManager() {}

    public static void configureDriver(String browser) {
        Configuration.browser = browser.toLowerCase();
        Configuration.headless = DEFAULT_HEADLESS_MODE;
        Configuration.browserSize = DEFAULT_BROWSER_SIZE;
        Configuration.timeout = DEFAULT_TIMEOUT;
    }

}
