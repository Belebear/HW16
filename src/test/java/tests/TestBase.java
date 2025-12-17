package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;


public class TestBase {

    static String body = """
             {"userName" : "%s",
             "password": "%s"}
            """;

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = System.getProperty("remote","https://user1:1234@selenoid.autotests.cloud/wd/hub");
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "127";
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    public void addAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @DisplayName("Авторизация и получение токена")
    public static Response getAuthResponse() {
        Response authCookie = given()
                .body(body.formatted(TestData.login, TestData.password))
                .contentType("application/json")
                .log().all()
                .when()
                .post("https://demoqa.com/Account/v1/Login")
                .then()
                .statusCode(200)
                .log().all()
                .body("$", hasKey("token"))
                .extract().response();
        return authCookie;
    }

    @AfterEach
    void closeWeb() {
        Selenide.closeWebDriver();
    }
}
