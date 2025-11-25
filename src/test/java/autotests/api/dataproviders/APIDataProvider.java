package autotests.api.dataproviders;

import org.testng.annotations.DataProvider;
import project.core.api.pojo.models.Player;

public class APIDataProvider {
    @DataProvider(name = "playerValidData")
    public static Object[][] playerValidData() {
        return new Object[][]{
                {
                        new Player
                                .Builder()
                                .setMandatoryName("test name")
                                .setMandatoryPlayerTitle("test title")
                                .setOptionalBrand("test brand")
                                .setOptionalLength(199)
                                .build()
                },
                {
                        new Player
                                .Builder()
                                .setMandatoryName("test name")
                                .setMandatoryPlayerTitle("test title")
                                .build()
                }
        };
    }

    @DataProvider(name = "playerInvalidData")
    public static Object[][] playerInvalidData() {
        return new Object[][]{
                {
                        new Player
                                .Builder()
                                .setMandatoryName(null)
                                .setMandatoryPlayerTitle("test title")
                                .setOptionalBrand("test brand")
                                .setOptionalLength(199)
                                .build()
                },
                {
                        new Player
                                .Builder()
                                .setMandatoryName("test name")
                                .setMandatoryPlayerTitle(null)
                                .setOptionalBrand("test brand")
                                .setOptionalLength(199)
                                .build()
                }
        };
    }
}
