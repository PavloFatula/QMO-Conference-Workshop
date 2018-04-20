package data;

public enum SortingType {
    NAME_ASC("Name (A - Z)"),
    NAME_DESC("Name (Z - A)"),
    PRICE_ASC("Price (Low > High)"),
    PRICE_DESC("Price (High > Low)"),
    RATING_ASC("Rating (Lowest)"),
    RATING_DESC("Rating (Highest)"),
    MODEL_ASC("Model (A - Z)"),
    MODEL_DESC("Model (Z - A)");

    private String name;

    private SortingType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
