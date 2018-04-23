package data.applications;

import data.IUrl;

public interface IApplicationSource {

    String getBrowserName();

    String getDriverPath();

    IUrl getBaseUrl();

    IUrl getSearchPageUrl();

}
