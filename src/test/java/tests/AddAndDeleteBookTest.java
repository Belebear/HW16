package tests;

import api.BooksApiSteps;
import api.ManageCookiesSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static io.qameta.allure.Allure.step;

@DisplayName("Добавление и удаление книги")
public class AddAndDeleteBookTest extends TestBase {
    ProfilePage profilePage = new ProfilePage();

    @DisplayName("Добавление и удаление книги")
    @Test
    @Tag("api_ui_test")
    void addAndDeleteBookTest() {

        BooksApiSteps.deleteBooksFromBasket();
        BooksApiSteps.addBookToBasket();
        ManageCookiesSteps.setCookiesForWebDriver();

        step("Удаление книги и проверка пустой талицы", () -> {
            profilePage
                    .openProfilePage()
                    .checkBook()
                    .deleteBookFromCollection()
                    .checkTableIsEmpty();
        });
    }
}
