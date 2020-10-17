package gmailsteps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginSteps {
    @Step("Логин на gmail.com")
    public static void loginToGmail(String userName, String password) {
        open("https://gmail.com");
        $("#identifierId").val(userName).pressEnter();
        $("#password input").val(password).pressEnter();
    }
}
