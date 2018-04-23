package data.applications;

import data.IUrl;
import data.Url;

public class ApplicationUrl implements IUrl {

    private final IUrl url;

    public ApplicationUrl(String path) {
        this(System.getProperty("sut-domain", "localhost"), path);
    }

    public ApplicationUrl(String domain, String path) {
        this(new Url(domain, path));
    }


    public ApplicationUrl(IUrl url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return url.toString();
    }
}
