package pages;

import pages.modules.FeaturedBlock;
import pages.modules.Header;
import org.openqa.selenium.WebDriver;

public class HomePage {
    protected WebDriver driver;
    private Header header;
    private FeaturedBlock featuredBlock;

    public HomePage() {
        this.driver = Application.get().getBrowser().getDriver();
        this.header = new Header();
        this.featuredBlock = new FeaturedBlock();
    }

    public Header getHeader() {
        return header;
    }

    public FeaturedBlock getFeaturedBlock() {
        return featuredBlock;
    }

    public SearchPage moveToSearchPage() {
        getHeader().clickSearchButton();
        return new SearchPage();
    }

    public SearchPage searchByKeyword(String keyword) {
        header.clearSearchField();
        header.sendTextToSearchField(keyword);
        header.clickSearchButton();
        return new SearchPage();
    }

    /* TODO implement next methods:
    clickLogo()
    register()
    login()
    moveToWishList()
    moveToShoppingCart()
    checkout()
    checkShoppingCart()
    selectProductsCategory()
     */
}
