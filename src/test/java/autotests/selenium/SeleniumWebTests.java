package autotests.selenium;

import autotests.settings.TestBaseSelenium;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.core.pages.selenium.HomePage;
import project.core.pages.selenium.enums.Languages;
import project.managers.Helpers;

import static project.config.Config.CONFIG;
import static project.config.Config.ConfigKey.HOME_PAGE_URL;
import static project.config.Config.ConfigKey.MAIN_PAGE_URL;
import static project.webdriver.WebDriverManager.getDriver;

public class SeleniumWebTests extends TestBaseSelenium {
    @Test(groups = "authorization", description = "Log in from main page and get redirected to home page")
    public void authorizationFromMainPage() {
        Helpers.mainPage().openLoginPageByButton();
        Helpers.login().loginWithValidEmailAndPassword(HomePage::new);
        Assert.assertEquals(getDriver().getCurrentUrl(), CONFIG.getConfig(HOME_PAGE_URL),
                "User wasn't redirected to home page after successful login");
    }

    @Test(groups = "templates", description = "Search template and check that the searched word is present as a keyword")
    public void searchTemplateAndCheckKeyword() {
        String word = "Cat";
        boolean isKeyword = Helpers.templatesPage().searchTemplateAndCheckKeywordInIt(1, word);
        Assert.assertTrue(isKeyword, "Keyword '" + word + "' is not present in found template");
    }

    @Test(groups = "language", description = "Change language on main page and check that url is changed")
    public void changeLanguage() {
        Helpers.mainPage().selectLanguage(Languages.SUOMI);
        Assert.assertEquals(getDriver().getCurrentUrl(), CONFIG.getConfig(MAIN_PAGE_URL) + "fi/",
                "URL wasn't changed after language selection");
    }
}