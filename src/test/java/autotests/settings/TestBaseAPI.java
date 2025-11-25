package autotests.settings;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import project.core.api.requests.PlayerAPI;

@Listeners(TestListener.class)
public abstract class TestBaseAPI {
    protected PlayerAPI playerAPI;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        playerAPI = new PlayerAPI();
    }
}