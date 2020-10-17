package gmail;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static gmailsteps.LoginSteps.loginToGmail;
import static utils.helpers.AttachmentsHelper.*;
import static utils.helpers.DriverHelper.*;
import static utils.helpers.EnvironmentHelper.isVideoOn;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BaseTest {

    @BeforeAll
    static void setUp() {
        configSelenide();
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        closeWebDriver();
        if (isVideoOn) attachVideo(sessionId);
    }
}
