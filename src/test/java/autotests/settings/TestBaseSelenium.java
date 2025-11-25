package autotests.settings;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import project.managers.Helpers;
import project.managers.Pages;
import project.webdriver.WebDriverManager;

@Listeners({TestListener.class, AllureScreenshotListener.class})
public abstract class TestBaseSelenium {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.setUp();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.tearDown();
        Pages.remove();
        Helpers.remove();
    }
}
