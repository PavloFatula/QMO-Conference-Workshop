package data.products;

import data.Currencies;

import java.util.Map;

public interface IProduct {
    String getSearchKey();

    String getName();

    String getDescription();

    Map<Enum<?>, Double> getPrices();

    double getPriceByCurrencyName(Currencies currencyName);
}
