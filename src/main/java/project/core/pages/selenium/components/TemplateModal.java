package project.core.pages.selenium.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import project.core.pages.selenium.base.BaseComponent;

import java.util.List;
import java.util.stream.Collectors;

public class TemplateModal extends BaseComponent {
    @FindBy(xpath = "//div[contains(@class, 'templateWrapper')]//div[contains(@class, 'keywords')]/a/div")
    private List<WebElement> keywordsText;
    @FindBy(xpath = "//div[contains(@class, 'templateWrapper')]//h1")
    private WebElement h1;

    public TemplateModal() {
        PageFactory.initElements(field -> new AjaxElementLocator(driver, field, 10) {
            @Override
            protected boolean isElementUsable(WebElement element) {
                return element.isDisplayed() && element.isEnabled();
            }
        }, this);
        webDriverWaitWithMsg("This is not Template modal, current page is: " +
                driver.getCurrentUrl(), 10)
                .until(ExpectedConditions.textToBePresentInElement(h1, "Template "));
    }

    public boolean isKeyword(String keyword) {
        return getKeywords().contains(keyword);
    }

    private List<String> getKeywords() {
        return keywordsText
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
