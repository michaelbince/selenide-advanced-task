package utils;

import com.codeborne.selenide.Selenide;

public class BrowserDataStorage {

    public static void clearCookies() {
        Selenide.clearBrowserCookies();
    }

    public static void clearLocalStorage() {
        Selenide.executeJavaScript("window.localStorage.clear();");
    }

    public static void clearSessionStorage() {
        Selenide.executeJavaScript("window.sessionStorage.clear();");
    }

    public static void clearAllData() {
        clearCookies();
        clearLocalStorage();
        clearSessionStorage();
    }
}
