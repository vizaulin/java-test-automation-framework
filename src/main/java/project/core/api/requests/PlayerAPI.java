package project.core.api.requests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;
import project.core.api.pojo.models.ErrorResponse;
import project.core.api.pojo.models.Player;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static project.config.Config.CONFIG;
import static project.config.Config.ConfigKey.POSTMAN_API_KEY;
import static project.config.Config.ConfigKey.POSTMAN_MOCK_URL;

public class PlayerAPI {
    private final RequestSpecification baseSpec = given()
            .filter(new AllureRestAssured())
            .baseUri(CONFIG.getConfig(POSTMAN_MOCK_URL))
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
            .header("x-api-key", CONFIG.getConfig(POSTMAN_API_KEY));

    private final RequestSpecification writeSpec = given().spec(baseSpec)
            .contentType(JSON);

    public Player createPlayer(Player player) {
        return writeSpec
                .body(player)
                .post("/player")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(Player.class);
    }

    public ErrorResponse createPlayerExpectingError(Player player) {
        return writeSpec
                .body(player)
                .post("/player")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(ErrorResponse.class);
    }

    public Player getPlayer(Integer id) {
        return baseSpec
                .pathParam("id", id)
                .get("/player/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Player.class);
    }
}
