package project.core.pages.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import project.core.pages.selenium.base.BasePage;
import project.core.pages.selenium.enums.Languages;

import java.util.List;

import static project.config.Config.CONFIG;
import static project.config.Config.ConfigKey.MAIN_PAGE_TITLE;
import static project.config.Config.ConfigKey.MAIN_PAGE_URL;

public class MainPage extends BasePage {
    private final By loginButton = By.xpath("//button[@data-value='signIn']");
    private final By languageSelector = By.xpath("//div[contains(@class, 'language-select__desktop')]");
    private final By selectedLanguage = By.xpath("//div[contains(@class, 'language-select__desktop')]/span");
    private final By languages = By.xpath("//div[contains(@class, 'language-select__desktop')]//li/a/span");

    public MainPage openPage() {
        driver.get(CONFIG.getConfig(MAIN_PAGE_URL));
        return waitPageToOpen();
    }

    public MainPage waitPageToOpen() {
        webDriverWaitWithMsg("This is not Main Page, current page is: " + driver.getCurrentUrl(), 10)
                .until(d -> d.getCurrentUrl().startsWith(CONFIG.getConfig(MAIN_PAGE_URL)) &&
                        d.getTitle().equals(CONFIG.getConfig(MAIN_PAGE_TITLE)));
        return this;
    }

    public MainPage selectLanguage(Languages language) {
        if (language.getName().equals(getSelectedLanguage())) {
            return this;
        }

        openLanguageSelector();
        WebElement languageElement = getLanguageElement(language);
        String currentUrl = driver.getCurrentUrl();
        scrollTo(languageElement);
        languageElement.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        return this;
    }

    private String getSelectedLanguage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selectedLanguage)).getText();
    }

    private void openLanguageSelector() {
        clickWhenReady(languageSelector);
    }

    private WebElement getLanguageElement(Languages language) {
        List<WebElement> languageNameElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(languages));
        return languageNameElements
                .stream()
                .filter(webElement -> language.getName().equals(webElement.getText()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Language '" + language.getName() + "' not found"));
    }

    public LoginPage clickLoginButton() {
        clickWhenReady(loginButton);
        return new LoginPage().waitPageToOpen();
    }
}
