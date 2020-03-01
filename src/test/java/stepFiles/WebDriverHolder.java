package stepFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverHolder {

//    private WebDriverHolder() {
//    }

    private static WebDriver instance = null;

    public static synchronized WebDriver getDriver() {
        if (instance == null) {
            instance = new ChromeDriver();
        }
        return instance;
    }

    public static synchronized void quitDriver() {
        if (instance != null) {
            instance.quit();
            instance = null;
        }
    }
}