package tests;

import models.AddBookRequestBodyModel;
import models.AddBookResponseBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ProfilePage;
import specs.RequestSpec;
import specs.ResponseSpec;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Добавление и удаление книги")
public class AddAndDeleteBookTest extends TestBase {
    ProfilePage profilePage = new ProfilePage();

    @DisplayName("Добавление и удаление книги")
    @Test
    @Tag("api_ui_test")
    void addAndDeleteBookTest() {
        step("Очистка таблицы", () -> {
            given(RequestSpec.baseSpec())
                    .when()
                    .delete("/BookStore/v1/Books?UserId=" + TestBase.getAuthResponse().path("userId"))
                    .then()
                    .spec(ResponseSpec.baseResp(204));
        });

        AddBookRequestBodyModel.CollectionOfIsbns collectionOne = AddBookRequestBodyModel.CollectionOfIsbns.builder()
                .isbn(TestData.isbn_1)
                .build();

        AddBookRequestBodyModel requestBody = AddBookRequestBodyModel.builder()
                .userId(TestBase.getAuthResponse().path("userId"))
                .collectionOfIsbns(List.of(collectionOne))
                .build();

        step("Отправка запроса на добавление книги", () -> {
            AddBookResponseBodyModel response =
                    given(RequestSpec.baseSpec())
                            .body(requestBody)
                            .when()
                            .post("/BookStore/v1/Books")
                            .then()
                            .spec(ResponseSpec.baseResp(201))
                            .extract().body().as(AddBookResponseBodyModel.class);
            assertEquals(response.getBooks().get(0).getIsbn(), collectionOne.getIsbn());
        });

        step("Добавление Cookie", () -> {
            profilePage
                    .openhttpsToolsqaImage();
            getWebDriver().manage().addCookie(new Cookie("userID", TestBase.getAuthResponse().path("userId")));
            getWebDriver().manage().addCookie(new Cookie("expires", TestBase.getAuthResponse().path("expires")));
            getWebDriver().manage().addCookie(new Cookie("token", TestBase.getAuthResponse().path("token")));
        });

        step("Удаление книги и проверка пустой талицы", () -> {
            profilePage
                    .openProfilePage()
                    .checkBook()
                    .deleteBookFromCollection()
                    .checkTableIsEmpty();
        });
    }
}
