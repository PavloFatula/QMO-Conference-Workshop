package pages.modules;

import pages.utils.ConciseAPI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Header extends ConciseAPI {
    private final String CURRENCY_BY_CSS = ".btn.btn-link.dropdown-toggle";
    private final String MY_ACCOUNT_BY_CSS = ".list-inline .dropdown";
    private final String WISH_LIST_BY_CSS = "#wishlist-total";
    private final String SHOPPING_CART_BY_CSS = "a[title='Shopping Cart']";
    private final String CHECKOUT_BY_CSS = "a[title='Checkout']";
    private final String SEARCH_FIELD_BY_CSS = "#search input";
    private final String SEARCH_BUTTON_BY_CSS = ".btn.btn-default.btn-lg";

    // TODO use driver from Application class
    public Header() {
        super();
        verifyWebElements();
    }

    private void verifyWebElements() {
        getCurrency();
        getMyAccount();
        getWishList();
        getShoppingCart();
        getCheckout();
        getSearchField();
        getSearchButton();
    }

    public WebElement getCurrency() {
        return $(CURRENCY_BY_CSS);
    }

    public String getCurrencyText() {
        return getCurrency().getText();
    }

    public void clickCurrency() {
        getCurrency().click();
    }

    public WebElement getMyAccount() {
        return $(MY_ACCOUNT_BY_CSS);
    }

    public void clickMyAccount() {
        getMyAccount().click();
    }

    public WebElement getWishList() {
        return $(WISH_LIST_BY_CSS);
    }

    public void clickWishList() {
        getWishList().click();
    }

    public WebElement getShoppingCart() {
        return $(SHOPPING_CART_BY_CSS);
    }

    public void clickShoppingCart() {
        getShoppingCart().click();
    }

    public WebElement getCheckout() {
        return $(CHECKOUT_BY_CSS);
    }

    public void clickCheckout() {
        getCheckout().click();
    }

    public WebElement getSearchField() {
        return $(SEARCH_FIELD_BY_CSS);
    }

    public void clearSearchField() {
        getSearchField().clear();
    }

    public void sendTextToSearchField(String text) {
        getSearchField().sendKeys(text);
    }

    public void enterSearchField() {
        getSearchField().sendKeys(Keys.ENTER);
    }

    public void submitSearchField() {
        getSearchField().submit();
    }

    public WebElement getSearchButton() {
        return $(SEARCH_BUTTON_BY_CSS);
    }

    public void clickSearchButton() {
        getSearchButton().click();
    }
}
