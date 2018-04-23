package data;

public class Url implements IUrl {

    private final String protocol;
    private final String domain;
    private final String path;

    public Url(String domain, String path) {
        this("http", domain, path);
    }

    public Url(String protocol, String domain, String path) {
        this.protocol = protocol;
        this.domain = domain;
        this.path = path;
    }

    @Override
    public String toString() {
        final String start = protocol + "://" + domain;
        if (path == null || path.isEmpty()) {
            return start;
        }
        if (path.startsWith("/")) {
            return start + path;
        }
        return start + "/" + path;
    }

}
