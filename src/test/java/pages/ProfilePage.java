package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private SelenideElement table = $(".ReactTable");
    private SelenideElement closeButtonOk = $("#closeSmallModal-ok");

    @Step("Открытие страницы profile")
    public ProfilePage openProfilePage() {
        open("/profile");
        return this;
    }

    @Step("Проверка книги в таблице")
    public ProfilePage checkBook() {
        $(table).shouldHave(text("Designing Evolvable Web APIs with ASP.NET"));
        return this;
    }

    @Step("Удаление книги из таблицы")
    public ProfilePage deleteBookFromCollection() {
        $$("#delete-record-undefined").first().click();
        $(closeButtonOk).click();
        return this;
    }

    @Step("Проверка что таблица пустая")
    public ProfilePage checkTableIsEmpty() {
        $(table).shouldHave(text("No rows found"));
        return this;
    }

    @Step("Открытие изображения для пробрасывания cookie")
    public ProfilePage openhttpsToolsqaImage() {
        open("https://demoqa.com/images/Toolsqa.jpg");
        return this;
    }
}
