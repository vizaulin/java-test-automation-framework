package project.helpers;

import io.qameta.allure.Step;
import project.core.pages.selenium.enums.Languages;
import project.managers.Pages;

public class MainHelper {

    @Step
    public void openLoginPageByButton() {
        Pages.mainPage().openPage().clickLoginButton();
    }

    @Step
    public void selectLanguage(Languages language) {
        Pages.mainPage().openPage().selectLanguage(language);
    }
}
