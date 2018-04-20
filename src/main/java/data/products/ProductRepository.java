package data.products;

import data.Currencies;
import pages.utils.CSVReader;
import pages.utils.ExcelReader;

import java.util.List;

public final class ProductRepository {

    private ProductRepository() {
    }

    public static IProduct macBook() {
        return Product.get()
                .setSearchKey("mac")
                .setName("MacBook")
                .setDescription("Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1")
                .setPrice(Currencies.EURO, 560.94)
                .setPrice(Currencies.POUND_STERLING, 487.62)
                .setPrice(Currencies.US_DOLLAR, 602.00)
                .buildProduct();
    }

    public static List<IProduct> fromCsvProducts() {
        return fromCsvProducts("products.csv");
    }

    public static List<IProduct> fromCsvProducts(String filename) {
        return Product.getByList(new CSVReader(filename).getAllCells());
    }

    public static List<IProduct> fromExcelProducts() {
        return fromExcelProducts("products.xlsx");
    }

    public static List<IProduct> fromExcelProducts(String filename) {
        return Product.getByList(new ExcelReader(filename).getAllCells());
    }

}