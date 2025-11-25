package project.core.pages.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import project.core.pages.selenium.base.BasePage;
import project.core.pages.selenium.components.TemplateModal;

import java.util.List;

import static project.config.Config.CONFIG;
import static project.config.Config.ConfigKey.TEMPLATES_PAGE_TITLE;
import static project.config.Config.ConfigKey.TEMPLATES_PAGE_URL;

public class TemplatesPage extends BasePage {
    private final By templatesList = By.xpath("//div[@data-test='template-link']");
    private final By searchClearButton = By.xpath("//div[contains(@class, 'searchContainer')]" +
            "//button[@aria-label='Clear']");
    private final By searchInput = By.xpath("//div[contains(@class, 'searchContainer')]//input");
    private final By searchButton = By.xpath("//div[contains(@class, 'searchContainer')]//button[@aria-label='Search']");

    public TemplatesPage openPage() {
        driver.get(CONFIG.getConfig(TEMPLATES_PAGE_URL));
        return waitPageToOpen();
    }

    public TemplatesPage waitPageToOpen() {
        webDriverWaitWithMsg("This is not Templates Page, current page is: " + driver.getCurrentUrl(), 10)
                .until(d -> d.getCurrentUrl().startsWith(CONFIG.getConfig(TEMPLATES_PAGE_URL)) &&
                        d.getTitle().contains(CONFIG.getConfig(TEMPLATES_PAGE_TITLE)));
        return this;
    }

    public TemplatesPage search(String text) {
        inputText(text);
        wait.until(ExpectedConditions.attributeToBe(searchInput, "value", text));
        List<WebElement> templates = getTemplates();
        clickSearchButton();
        templates.forEach(template -> wait.until(ExpectedConditions.stalenessOf(template)));
        return this;
    }

    private void inputText(String text) {
        clearSearchInput();
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).sendKeys(text);
    }

    private void clearSearchInput() {
        String currentValue = wait.until(ExpectedConditions.elementToBeClickable(searchInput)).getDomAttribute("value");
        if (currentValue != null && !currentValue.equals("")) {
            clickWhenReady(searchClearButton);
        }
    }

    private List<WebElement> getTemplates() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(templatesList));
    }

    private void clickSearchButton() {
        clickWhenReady(searchButton);
    }

    public TemplateModal openTemplate(int number) {
        getTemplates().get(number).click();
        return new TemplateModal();
    }
}
