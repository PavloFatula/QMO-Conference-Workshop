package data.applications;

public class ApplicationSourceRepository {
    private ApplicationSourceRepository() {}

    public static IApplicationSource defaultParameters() {
        return OpencarttChrome();
    }

    public static IApplicationSource OpencarttChrome() {
        return new ApplicationSource(
                "ChromeTemporary",
                "src/main/resources/chromedriver.exe",
                new ApplicationUrl(""),
                new ApplicationUrl("index.php?route=product/search"));
    }

    public static IApplicationSource OpencarttFirefox() {
        return new ApplicationSource(
                "FirefoxTemporary",
                "C:/Program Files/Mozilla Firefox/browser/geckodriver.exe",
                new ApplicationUrl(""),
                new ApplicationUrl("index.php?route=product/search"));
    }

    public static IApplicationSource cliOption() {
        return new ApplicationSource(
                System.getProperty("browser"),
                null,
                new ApplicationUrl(""),
                new ApplicationUrl("index.php?route=product/search"));
    }
}
