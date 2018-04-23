package data.applications;

import data.IUrl;

public class ApplicationSource implements IApplicationSource {
    private String browserName;
    private String driverPath;
    private IUrl baseUrl;
    private IUrl searchPageUrl;

    public ApplicationSource(String browserName, String driverPath, IUrl baseUrl, IUrl searchPageUrl) {
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

    public IUrl getBaseUrl() {
        return baseUrl;
    }

    public IUrl getSearchPageUrl() {
        return searchPageUrl;
    }
}