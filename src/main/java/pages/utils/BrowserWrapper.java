package pages.utils;

import data.IUrl;
import data.applications.IApplicationSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserWrapper {

    private interface IBrowser {
        WebDriver getBrowser(IApplicationSource applicationSource);
    }

    // static classes for browser creating----------------------------------------------------------
    private static class FirefoxTemporary implements IBrowser {
        public WebDriver getBrowser(IApplicationSource applicationSource) {
            System.setProperty("webdriver.gecko.driver", applicationSource.getDriverPath());
            return new FirefoxDriver();
        }
    }

    private static class ChromeTemporary implements IBrowser {
        public WebDriver getBrowser(IApplicationSource applicationSource) {
            System.setProperty("webdriver.chrome.driver", applicationSource.getDriverPath());
            return new ChromeDriver();
        }
    }

    private static class ChromeProfile implements IBrowser {
        public WebDriver getBrowser(IApplicationSource applicationSource) {
            System.setProperty("webdriver.chrome.driver", applicationSource.getDriverPath());
            String userProfile = System.getenv("HOMEPATH")
                    + "\\AppData\\Local\\Google\\Chrome\\User Data";
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-data-dir=" + userProfile); // Working in Windows.
            return new ChromeDriver(options);
        }
    }

    private static class ChromeWithoutUI implements IBrowser {
        public WebDriver getBrowser(IApplicationSource applicationSource) {
            System.setProperty("webdriver.chrome.driver",
                    applicationSource.getDriverPath());
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-proxy-server");
            options.addArguments("--ignore-certificate-errors");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            return driver;
        }
    }

    private void initWebDriver(IApplicationSource applicationSource) {
        Browsers currentBrowser = Browsers.DEFAULT_TEMPORARY;
        for (Browsers browser : Browsers.values()) {
            if (browser.toString().equalsIgnoreCase(applicationSource.getBrowserName())) {
                currentBrowser = browser;
                break;
            }
        }
        driver = currentBrowser.runBrowser(applicationSource);
    }

    // ENUM----------------------------------------------------------------------------
    public static enum Browsers {
        DEFAULT_TEMPORARY("ChromeTemporary", new ChromeTemporary()),
        FIREFOX5X_TEMPORARY("FireFox5xTemporary", new FirefoxTemporary()),
        CHROME_TEMPORARY("ChromeTemporary", new ChromeTemporary()),
        CHROME_PROFILE("ChromeProfile", new ChromeProfile()),
        CHROME_WITHOUT_UI("ChromeWithoutUI", new ChromeWithoutUI()),
        CHROME("chrome", new Chrome()),
        FIREFOX("firefox", new Firefox()),
        RC("remote-chrome", new RChrome(new HubUrl())),
        RF("remote-firefox", new RFirefox(new HubUrl()));
        //
        private String browserName;
        private IBrowser browser;

        private Browsers(String browserName, IBrowser browser) {
            this.browserName = browserName;
            this.browser = browser;
        }

        public WebDriver runBrowser(IApplicationSource applicationSource) {
            return browser.getBrowser(applicationSource);
        }

        @Override
        public String toString() {
            return browserName;
        }
    }

    private static class RChrome implements IBrowser {
        private final IUrl hub;

        RChrome(IUrl hub) {
            this.hub = hub;
        }

        public WebDriver getBrowser(IApplicationSource applicationSource) {
            try {
                return new RemoteWebDriver(new URL(hub.toString()), new ChromeOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class RFirefox implements IBrowser {
        private final IUrl hub;

        RFirefox(IUrl hub) {
            this.hub = hub;
        }
        public WebDriver getBrowser(IApplicationSource applicationSource) {
            try {
                return new RemoteWebDriver(new URL(hub.toString()), new FirefoxOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class Chrome implements IBrowser {
        public WebDriver getBrowser(IApplicationSource applicationSource) {
            return new ChromeDriver();
        }
    }

    //browser wrapper-----------------------------------------------------------------
    private WebDriver driver;

    //constructor-------------------------------------------------------------------------
    public BrowserWrapper(IApplicationSource applicationSource) {
        initWebDriver(applicationSource);
    }

    private static class Firefox implements IBrowser {
        public WebDriver getBrowser(IApplicationSource applicationSource) {
            return new FirefoxDriver();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public File getScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
    }

    public String getSourceCode() {
        return getDriver().getPageSource();
    }

    public void openUrl(String url) {
        getDriver().get(url);
    }

    public void navigateForward() {
        getDriver().navigate().forward();
    }

    public void navigateBack() {
        getDriver().navigate().back();
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public void quit() {
        if (getDriver() != null) {
            getDriver().quit();
            driver = null;
        }
    }
}