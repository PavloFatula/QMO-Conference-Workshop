package com.softserve.edu.test;

import pages.Application;
import pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestHomePage extends BaseTest {
    @DataProvider
    public Object[][] searchProvider() {
        return new Object[][] {
                {"mac", "http://opencartt.rf.gd/index.php?route=product/search&search=mac"}
        };
    }

    @Test(dataProvider = "searchProvider")
    public void testSearch(String keyword, String expectedUrl) {
        SearchPage page = Application
                .get()
                .loadHomePage()
                .searchByKeyword(keyword);
        Assert.assertEquals(page.getCurrentUrl(),
                expectedUrl);
    }
}