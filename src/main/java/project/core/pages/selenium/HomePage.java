package project.core.pages.selenium;

import project.core.pages.selenium.base.BasePage;

import static project.config.Config.CONFIG;
import static project.config.Config.ConfigKey.HOME_PAGE_TITLE;
import static project.config.Config.ConfigKey.HOME_PAGE_URL;

public class HomePage extends BasePage {

    public HomePage waitPageToOpen() {
        webDriverWaitWithMsg("This is not HomePage, current page is: " + driver.getCurrentUrl(), 10)
                .until(d -> d.getCurrentUrl().startsWith(CONFIG.getConfig(HOME_PAGE_URL)) &&
                        d.getTitle().equals(CONFIG.getConfig(HOME_PAGE_TITLE)));
        return this;
    }
}
