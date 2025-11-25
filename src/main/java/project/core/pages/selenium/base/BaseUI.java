package project.core.pages.selenium.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.webdriver.WebDriverManager;

import java.time.Duration;

public abstract class BaseUI {
    protected final WebDriver driver = WebDriverManager.getDriver();
    protected final WebDriverWait wait = webDriverWait(Duration.ofSeconds(10));

    protected WebDriverWait webDriverWaitWithMsg(String onErrorMsg, int seconds) {
        WebDriverWait wait = webDriverWait(Duration.ofSeconds(seconds));
        wait.withMessage(onErrorMsg);
        return wait;
    }

    private WebDriverWait webDriverWait(Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    protected WebElement clickWhenReady(By locator) {
        return wait.until(d -> {
            WebElement element = d.findElement(locator);
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
                return element;
            } else {
                return null;
            }
        });
    }

    protected void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
