package project.managers;

import project.core.pages.selenium.HomePage;
import project.core.pages.selenium.LoginPage;
import project.core.pages.selenium.MainPage;
import project.core.pages.selenium.TemplatesPage;

public class Pages {
    private static final ThreadLocal<LoginPage> loginPage =
            ThreadLocal.withInitial(LoginPage::new);

    private static final ThreadLocal<MainPage> mainPage =
            ThreadLocal.withInitial(MainPage::new);

    private static final ThreadLocal<HomePage> homePage =
            ThreadLocal.withInitial(HomePage::new);

    private static final ThreadLocal<TemplatesPage> templatesPage =
            ThreadLocal.withInitial(TemplatesPage::new);

    public static LoginPage loginPage() {
        return loginPage.get();
    }

    public static MainPage mainPage() {
        return mainPage.get();
    }

    public static HomePage homePage() {
        return homePage.get();
    }

    public static TemplatesPage templatesPage() {
        return templatesPage.get();
    }

    public static void remove() {
        loginPage.remove();
        mainPage.remove();
        homePage.remove();
        templatesPage.remove();
    }
}
