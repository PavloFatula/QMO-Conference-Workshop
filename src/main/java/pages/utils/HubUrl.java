package pages.utils;

import data.IUrl;
import data.Url;

public class HubUrl implements IUrl {

    private final IUrl url;

    public HubUrl() {
        this.url = new Url(System.getProperty("hub-domain", "selenium:4444"), "/wd/hub");
    }

    @Override
    public String toString() {
        return url.toString();
    }
}
