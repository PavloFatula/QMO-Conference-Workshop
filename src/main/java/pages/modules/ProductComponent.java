package pages.modules;

import pages.utils.ConciseAPI;
import pages.utils.RegexUtils;
import org.openqa.selenium.WebElement;

public class ProductComponent extends ConciseAPI {
    private WebElement productLayout;
    private final String NAME_BY_CSS = "h4 a";
    private final String DESCRIPTION_BY_CSS = ".caption > p:nth-child(2)";
    private final String PRICE_BY_CSS = ".caption .price";
    private final String ADD_TO_CART_BY_CSS = ".fa.fa-shopping-cart";
    private final String ADD_TO_WISH_BY_CSS = ".fa.fa-heart";
    private final String COMPARE_BY_CSS = ".fa.fa-exchange";
    private final String CLASS_ATTRIBUTE = "class";

    public ProductComponent(WebElement productLayout) {
        super();
        this.productLayout = productLayout;
        verifyWebElements();
    }

    private void verifyWebElements() {
        getName();
        getPrice();
        getDescription();
        getAddToCart();
        getAddToWish();
        getCompare();
    }

    public WebElement getName() {
        return findElementInLayoutByCss(productLayout, NAME_BY_CSS);
    }

    public String getNameText() {
        return getName().getText();
    }

    public void clickName() {
        getName().click();
    }

    public WebElement getPrice() {
        return findElementInLayoutByCss(productLayout, PRICE_BY_CSS);
    }

    public double getPriceAmount() {
        return RegexUtils.extractFirstDouble(getPrice().getText());
    }

    public WebElement getDescription() {
        return findElementInLayoutByCss(productLayout, DESCRIPTION_BY_CSS);
    }

    public String getDescriptionText() {
        return getDescription().getText();
    }

    public WebElement getAddToCart() {
        return findElementInLayoutByCss(productLayout, ADD_TO_CART_BY_CSS);
    }

    public void clickAddToCart() {
        getAddToCart().click();
    }

    public WebElement getAddToWish() {
        return findElementInLayoutByCss(productLayout, ADD_TO_WISH_BY_CSS);
    }

    public void clickAddToWish() {
        getAddToWish().click();
    }

    public WebElement getCompare() {
        return findElementInLayoutByCss(productLayout, COMPARE_BY_CSS);
    }

    public void clickCompare() {
        getCompare().click();
    }

    public String getElementClasses() {
        return productLayout.getAttribute(CLASS_ATTRIBUTE);
    }
}
