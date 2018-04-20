package data.applications;

public class ApplicationSource implements IApplicationSource {
    private String browserName;
    private String driverPath;
    private String baseUrl;
    private String searchPageUrl;

    // TODO Develop Builder
    public ApplicationSource(String browserName, String driverPath, String baseUrl, String searchPageUrl) {
        this.browserName = browserName;
        this.driverPath = driverPath;
        this.baseUrl = baseUrl;
        this.searchPageUrl = searchPageUrl;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSearchPageUrl() {
        return searchPageUrl;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setSearchPageUrl(String searchPageUrl) {
        this.searchPageUrl = searchPageUrl;
    }
}