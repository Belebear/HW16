package api;

import io.restassured.response.Response;
import lombok.Getter;
import util.TestData;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;


public class AuthorizationApiSteps {

    private static final String body = """
             {"userName" : "%s",
             "password": "%s"}
            """;

    @Getter
    private static String userId;
    @Getter
    private static String expires;
    @Getter
    private static String token;

    public static void auth() {
        step("Авторизация", () -> {
            Response authCookie = given()
                    .body(body.formatted(TestData.LOGIN, TestData.PASSWORD))
                    .contentType("application/json")
                    .log().all()
                    .when()
                    .post("https://demoqa.com/Account/v1/Login")
                    .then()
                    .statusCode(200)
                    .log().all()
                    .body("$", hasKey("token"))
                    .extract().response();
            userId = authCookie.path("userId");
            expires = authCookie.path("expires");
            token = authCookie.path("token");
        });
    }
}
