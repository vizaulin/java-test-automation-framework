package autotests.api;

import autotests.api.dataproviders.APIDataProvider;
import autotests.settings.TestBaseAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.core.api.pojo.models.ErrorResponse;
import project.core.api.pojo.models.Player;

public class APITests extends TestBaseAPI {
    @Test(groups = "player", dataProvider = "playerValidData", dataProviderClass = APIDataProvider.class)
    public void createPlayer(Player player) {
        Player createdPlayer = playerAPI.createPlayer(player);
        Assert.assertNotNull(createdPlayer.getId(), "Created player ID shouldn't be null");
        Assert.assertTrue(player.equalsIgnoringId(createdPlayer),
                "Created player doesn't match expected data (ignoring ID)");
    }

    @Test(groups = "player", dataProvider = "playerInvalidData", dataProviderClass = APIDataProvider.class)
    public void createPlayerExpectingError(Player player) {
        ErrorResponse response = playerAPI.createPlayerExpectingError(player);
        Assert.assertEquals(response.getError(), "VALIDATION_FAILED",
                "Expected validation error for invalid player data: " + player);
    }

    @Test(groups = "player")
    public void createdPlayerCanBeRetrievedById() {
        Player player = new Player
                .Builder()
                .setMandatoryName("test name")
                .setMandatoryPlayerTitle("test title")
                .setOptionalBrand("test brand")
                .setOptionalLength(199)
                .build();

        Player createdPlayer = playerAPI.createPlayer(player);
        Assert.assertNotNull(createdPlayer.getId(), "Created player ID shouldn't be null");

        Player retrievedPlayer = playerAPI.getPlayer(createdPlayer.getId());
        Assert.assertTrue(player.equalsIgnoringId(createdPlayer),
                "Created player doesn't match expected data (ignoring ID)");
        Assert.assertTrue(createdPlayer.equalsIgnoringId(retrievedPlayer),
                "Retrieved by ID player doesn't match created player (ignoring ID)");
    }
}