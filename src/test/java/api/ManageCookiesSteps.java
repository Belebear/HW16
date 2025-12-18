package api;

import org.openqa.selenium.Cookie;
import pages.ProfilePage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class ManageCookiesSteps {

    public static void setCookiesForWebDriver() {
        step("Добавление Cookie", () -> {
            ProfilePage profilePage = new ProfilePage();
            profilePage
                    .openhttpsToolsqaImage();
            getWebDriver().manage().addCookie(new Cookie("userID", AuthorizationApiSteps.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", AuthorizationApiSteps.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", AuthorizationApiSteps.getToken()));
        });
    }
}
