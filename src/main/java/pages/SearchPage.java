package pages;

import data.Categories;
import data.ProductsLimitOnPage;
import data.SortingType;
import pages.modules.ExtendedSearchBlock;
import pages.modules.Header;
import pages.modules.SearchResultsBlock;
import pages.utils.Helpers;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    private Header header;
    private ExtendedSearchBlock extendedSearchBlock;
    private SearchResultsBlock searchResultsBlock;
    private final String LIST_LAYOUT_CLASS = "product-list";
    private final String GRID_LAYOUT_CLASS = "product-grid";
    public WebDriver driver;

    public SearchPage() {
        this.driver = Application.get().getBrowser().getDriver();
        this.header = new Header();
        this.extendedSearchBlock = new ExtendedSearchBlock();
        this.searchResultsBlock = new SearchResultsBlock();
    }

    public SearchResultsBlock getSearchResultsBlock() {
        return searchResultsBlock;
    }

    public SearchPage makeExtendedSearch(String keyWord, Categories category) {
        return extendedSearchBlock.clearSearchField()
                .inputToSearchField(keyWord)
                .selectCategory(category)
                .clickSelectInDescriptionsCheckbox()
                .clickSearchButton();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void displayProductsAsList() {
        searchResultsBlock.clickListViewButton();
    }

    public void displayProductsAsGrid() {
        searchResultsBlock.clickGridViewButton();
    }

    public SearchPage sortProducts(SortingType type) {
        searchResultsBlock.selectSortingType(type);
        return new SearchPage();
    }

    public  SearchPage changeProductsLimitOnPage(ProductsLimitOnPage limit) {
        searchResultsBlock.selectQuantityOnPage(limit);
        return new SearchPage();
    }

    public SearchPage moveToNextSearchResultsPage() {
        searchResultsBlock.clickNextSearchResultsPage();
        return new SearchPage();
    }

    public int countProductsFound() {
        return searchResultsBlock.getProductComponents().size();
    }

    public boolean isKeywordInProductsNames(String keyword) {
        boolean result = true;
        for(String currentName: getSearchResultsBlock().getAllProductComponentsNames()) {
            if(!currentName.contains(keyword)) {
                result = false;
            }
        }
        return result;
    }

    public boolean isPricesSortedByAsc() {
        return Helpers.isArraySortedByAsc(searchResultsBlock.getAllProductsPricesAmounts());
    }

    public boolean isPricesSortedByDesc() {
        return Helpers.isArraySortedByDesc(searchResultsBlock.getAllProductsPricesAmounts());
    }

    public boolean isProductDisplayedAsList() {
        return searchResultsBlock.checkClassPresenceInProductComponent(LIST_LAYOUT_CLASS);
    }

    public boolean isProductDisplayedAsGrid() {
        return searchResultsBlock.checkClassPresenceInProductComponent(GRID_LAYOUT_CLASS);
    }

    public boolean isTitleContainKeyword(String keyword) {
        return driver.getTitle().contains(keyword);
    }
}
