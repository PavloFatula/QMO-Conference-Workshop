package pages.utils;

import pages.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ConciseAPI {
    public WebDriver driver;
    private WebDriverWait myWait;

    public ConciseAPI() {
        this.driver = Application.get().getBrowser().getDriver();
        this.myWait = new WebDriverWait(this.driver, 10);
    }


    public WebElement $(String cssSelector) {
        return myWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    public WebElement findByXpath(String xpath) {
        return myWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(xpath)));
    }

    public List<WebElement> $$(String cssSelector) {
        return myWait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
    }

    public WebElement findElementInLayoutByCss(WebElement layoutElement, String cssSelector) {
        return myWait.until(ExpectedConditions.visibilityOf(layoutElement
                .findElement(By.cssSelector(cssSelector))));
    }
}
