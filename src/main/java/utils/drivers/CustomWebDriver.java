package utils.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import static utils.helpers.EnvironmentHelper.*;

public class CustomWebDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        capabilities.setBrowserName(browser);
        capabilities.setVersion(version);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", isVideoOn);
        capabilities.setCapability("videoFrameRate", 24);

        if (isRemoteDriver) {
            return getRemoteWebDriver(capabilities);
        } else {
            WebDriverManager.chromedriver().setup();
            return getLocalChromeDriver(getChromeOptions().merge(capabilities));
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--disable-notifications")
                .addArguments("--disable-infobars");
        return chromeOptions;
    }

    private WebDriver getLocalChromeDriver(ChromeOptions chromeOptions) {
        return new ChromeDriver(chromeOptions);
    }

    private WebDriver getRemoteWebDriver(DesiredCapabilities capabilities) {
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(getRemoteWebdriverUrl(), capabilities);
        remoteWebDriver.setFileDetector(new LocalFileDetector());
        return remoteWebDriver;
    }

    private URL getRemoteWebdriverUrl() {
        try {
            return new URL(remoteDriverUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
