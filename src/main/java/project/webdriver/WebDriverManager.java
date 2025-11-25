package project.webdriver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver not initialized. Call setUp() first.");
        }
        return driver.get();
    }


    public static void setUp() {
        String runType = System.getProperty("runType", "local");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        try {
            if ("remote".equalsIgnoreCase(runType)) {
                String gridUrl = System.getProperty("gridUrl", "http://localhost:4444");
                driver.set(new RemoteWebDriver(new URL(gridUrl), options));
            } else {
                driver.set(new ChromeDriver(options));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create WebDriver", e);
        }
    }

    public static void tearDown() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}