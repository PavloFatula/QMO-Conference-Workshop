package pages.modules;

import data.ProductsLimitOnPage;
import data.SortingType;
import pages.utils.RegexUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchResultsBlock extends FeaturedBlock {
    public SearchResultsBlock() {
        super();
    }

    private final String PRODUCT_COMPARE_LINK_BY_CSS = "#compare-total";
    private final String LIST_VIEW_BUTTON_BY_CSS = "#list-view";
    private final String GRID_VIEW_BUTTON_BY_CSS = "#grid-view";
    private final String SORTING_TYPE_BY_CSS = "#input-sort";
    private final String QUANTITY_ON_PAGE_BY_CSS = "#input-limit";
    private final String NEXT_RESULT_PAGE_BY_XPATH = "//ul[@class='pagination'] /li /a[text()='>']";
    private final String PAGE_NUMBER_DESCRIPTION_BY_CSS = "div.text-right";

    public WebElement getProductCompareLink() {
        return $(PRODUCT_COMPARE_LINK_BY_CSS);
    }

    public String getProductCompareLinkText() {
        return getProductCompareLink().getText();
    }

    public int getProductComparisonAmount() {
        return RegexUtils.extractFirstNumber(getProductCompareLinkText());
    }

    public void clickListViewButton() {
        $(LIST_VIEW_BUTTON_BY_CSS).click();
    }

    public void clickGridViewButton() {
        $(GRID_VIEW_BUTTON_BY_CSS).click();
    }

    public void selectSortingType(SortingType sortingType) {
        Select dropdown = new Select($(SORTING_TYPE_BY_CSS));
        dropdown.selectByVisibleText(sortingType.toString());
    }

    public void selectQuantityOnPage(ProductsLimitOnPage quantity) {
        Select dropdown = new Select($(QUANTITY_ON_PAGE_BY_CSS));
        dropdown.selectByVisibleText(quantity.toString());
    }

    public void clickNextSearchResultsPage() {
        findByXpath(NEXT_RESULT_PAGE_BY_XPATH).click();
    }

    public String getPageNumberDescriptionText() {
        return $(PAGE_NUMBER_DESCRIPTION_BY_CSS).getText();
    }
}
