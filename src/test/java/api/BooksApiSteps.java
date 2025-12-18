package api;

import models.AddBookRequestBodyModel;
import models.AddBookResponseBodyModel;
import specs.RequestSpec;
import specs.ResponseSpec;
import util.Endpoints;
import util.TestData;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooksApiSteps {

    public static void deleteBooksFromBasket() {
        step("Удаление книг", () -> {
            given(RequestSpec.baseSpec())
                    .when()
                    .queryParam("UserId", AuthorizationApiSteps.getUserId())
                    .delete(Endpoints.BOOKSTORE_V1_BOOKS)
                    .then()
                    .spec(ResponseSpec.baseResp(204));
        });
    }

    public static void addBookToBasket() {
        AddBookRequestBodyModel.CollectionOfIsbns collectionOne = AddBookRequestBodyModel.CollectionOfIsbns.builder()
                .isbn(TestData.ISBN_1)
                .build();

        AddBookRequestBodyModel requestBody = AddBookRequestBodyModel.builder()
                .userId(AuthorizationApiSteps.getUserId())
                .collectionOfIsbns(List.of(collectionOne))
                .build();

        step("Отправка запроса на добавление книги", () -> {
            AddBookResponseBodyModel response =
                    given(RequestSpec.baseSpec())
                            .body(requestBody)
                            .when()
                            .post(Endpoints.BOOKSTORE_V1_BOOKS)
                            .then()
                            .spec(ResponseSpec.baseResp(201))
                            .extract().body().as(AddBookResponseBodyModel.class);
            assertEquals(response.getBooks().get(0).getIsbn(), collectionOne.getIsbn());
        });
    }
}
