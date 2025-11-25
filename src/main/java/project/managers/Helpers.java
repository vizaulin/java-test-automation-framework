package project.managers;

import project.helpers.LoginHelper;
import project.helpers.MainHelper;
import project.helpers.TemplatesHelper;

public class Helpers {
    private static final ThreadLocal<LoginHelper> loginHelper =
            ThreadLocal.withInitial(LoginHelper::new);

    private static final ThreadLocal<MainHelper> mainHelper =
            ThreadLocal.withInitial(MainHelper::new);

    private static final ThreadLocal<TemplatesHelper> templatesHelper =
            ThreadLocal.withInitial(TemplatesHelper::new);

    public static LoginHelper login() {
        return loginHelper.get();
    }

    public static MainHelper mainPage() {
        return mainHelper.get();
    }

    public static TemplatesHelper templatesPage() {
        return templatesHelper.get();
    }

    public static void remove() {
        loginHelper.remove();
        mainHelper.remove();
        templatesHelper.remove();
    }
}
