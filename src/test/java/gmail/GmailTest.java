package gmail;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static gmailsteps.LoginSteps.loginToGmail;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Owner("evgdas")
@Feature("Работа с письмами gmail")
@Tag("gmail")
public class GmailTest extends BaseTest {
    final public static String userName = System.getProperty("gmail.username");
    final public static String password = System.getProperty("gmail.password");
    final public String nameFrom = System.getProperty("gmail.nameFrom");

    public int quantityLetters;
    public String email;

    @Test
    @DisplayName("Отправка письма с количеством писем")
    public void sendQuantatyMail() {
        //степовая модель для тестов
        loginToGmail(userName, password);

        //а простые тесты можно по шагам в тесте более наглядно
        step("Получаем количество писем", () -> {
            email = $(byName(nameFrom)).getAttribute("email");
            quantityLetters = $$(byName(nameFrom)).size() / 2;
        });

        parameter("Количество писем: ", quantityLetters);
        parameter("Обратный адрес:", email);

        step("Отправка письма", () -> {
            $(byText("Написать")).click();
            $(byName("to")).val(email).pressTab();
            $(byName("subjectbox")).val("Letter from qatest!").pressTab();

            $(".editable").val("Quantity is " + quantityLetters).pressEnter();
            $(byText("Отправить")).click();

            $(withText("Письмо отправлено")).shouldBe(visible);
        });
    }
}
