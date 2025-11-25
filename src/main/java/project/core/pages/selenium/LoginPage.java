package project.core.pages.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import project.core.pages.selenium.base.BasePage;

import java.util.Set;
import java.util.function.Supplier;

import static project.config.Config.CONFIG;
import static project.config.Config.ConfigKey.*;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//button[@data-categ='logInPage']")
    private WebElement loginButton;
    @FindBy(xpath = "//input[@id='sign-in-email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='signInPassword']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@data-testid='submit']")
    private WebElement submitButton;

    public LoginPage() {
        PageFactory.initElements(field -> new AjaxElementLocator(driver, field, 10) {
            @Override
            protected boolean isElementUsable(WebElement element) {
                return element.isDisplayed() && element.isEnabled();
            }
        }, this);
    }

    public LoginPage openPage() {
        driver.get(CONFIG.getConfig(LOGIN_PAGE_URL));
        return waitPageToOpen();
    }

    public LoginPage waitPageToOpen() {
        webDriverWaitWithMsg("This is not Login Page, current page is: " + driver.getCurrentUrl(), 10)
                .until(d -> d.getCurrentUrl().startsWith(CONFIG.getConfig(LOGIN_PAGE_URL)) &&
                        d.getTitle().equals(CONFIG.getConfig(LOGIN_PAGE_TITLE)));
        return this;
    }

    public BasePage loginAndRedirectTo(Supplier<BasePage> pageSupplier) {
        String currentWindow = driver.getWindowHandle();
        clickLoginButton();
        switchToAuthorizationWindow(currentWindow);
        fillEmailAndPasswordAndClickSubmit(CONFIG.getConfig(VALID_EMAIL), CONFIG.getConfig(VALID_PASSWORD));
        driver.switchTo().window(currentWindow);
        return pageSupplier.get().waitPageToOpen();
    }

    private void clickLoginButton() {
        loginButton.click();
        webDriverWaitWithMsg("New window didn't appear after clicking the login button", 10)
                .until(d -> driver.getWindowHandles().size() > 1);
    }

    private void switchToAuthorizationWindow(String currentWindow) {
        Set<String> allWindows = driver.getWindowHandles();

        String authorizationWindow = allWindows
                .stream()
                .filter(window -> !window.equals(currentWindow))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New window is not found"));
        driver.switchTo().window(authorizationWindow);
    }

    private void fillEmailAndPasswordAndClickSubmit(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitButton.click();
    }
}
