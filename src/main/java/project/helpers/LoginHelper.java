package project.helpers;

import io.qameta.allure.Step;
import project.core.pages.selenium.base.BasePage;
import project.managers.Pages;

import java.util.function.Supplier;

public class LoginHelper {

    @Step
    public void loginWithValidEmailAndPassword(Supplier<BasePage> pageSupplier) {
        Pages.loginPage().loginAndRedirectTo(pageSupplier);
    }
}
