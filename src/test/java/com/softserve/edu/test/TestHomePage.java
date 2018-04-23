package com.softserve.edu.test;

import data.IUrl;
import data.applications.ApplicationUrl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Application;
import pages.SearchPage;

public class TestHomePage extends BaseTest {
    @DataProvider
    public Object[][] searchProvider() {
        return new Object[][] {
                {"mac", new ApplicationUrl("index.php?route=product/search&search=mac")}
        };
    }

    @Test(dataProvider = "searchProvider")
    public void testSearch(String keyword, IUrl expectedUrl) {
        SearchPage page = Application
                .get()
                .loadHomePage()
                .searchByKeyword(keyword);
        Assert.assertEquals(page.getCurrentUrl(), expectedUrl.toString());
    }
}