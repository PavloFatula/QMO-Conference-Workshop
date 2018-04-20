package data;

public enum  ProductsLimitOnPage {
    DEFAULT_LIMIT_15("15"),
    LIMIT_18("18"),
    LIMIT_25("25"),
    LIMIT_50("50"),
    LIMIT_75("75"),
    LIMIT_100("100");


    private String name;

    private ProductsLimitOnPage(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int toInt() {
        return Integer.parseInt(this.toString());
    }
}
