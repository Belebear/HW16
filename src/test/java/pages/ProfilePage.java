package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private SelenideElement table = $(".ReactTable");
    private SelenideElement closeButtonOk = $("#closeSmallModal-ok");

    public ProfilePage openProfilePage() {
        open("/profile");
        return this;
    }

    public ProfilePage checkBook() {
        $(table).shouldHave(text("Designing Evolvable Web APIs with ASP.NET"));
        return this;
    }

    public ProfilePage deleteBookFromCollection() {
        $$("#delete-record-undefined").first().click();
        $(closeButtonOk).click();
        return this;
    }

    public ProfilePage checkTableIsEmpty() {
        $(table).shouldHave(text("No rows found"));
        return this;
    }

    public ProfilePage openhttpsToolsqaImage() {
        open("https://demoqa.com/images/Toolsqa.jpg");
        return this;
    }
}
