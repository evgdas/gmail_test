package utils.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.drivers.CustomWebDriver;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;
import static utils.helpers.AttachmentsHelper.attachAsText;

public class DriverHelper {

    public static void configSelenide() {
        Configuration.browser = CustomWebDriver.class.getName();
        Configuration.timeout = 10000;
    }

    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

    public static String getSessionId() {
        attachAsText("SessionId", ((RemoteWebDriver) getWebDriver()).getSessionId().toString().replace("selenoid", ""));
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString().replace("selenoid", "");
    }
}