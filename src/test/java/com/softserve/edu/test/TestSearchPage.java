package com.softserve.edu.test;

import data.Categories;
import data.ProductsLimitOnPage;
import data.SortingType;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.Application;
import pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSearchPage extends BaseTest {

    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test opens home page and moves to search page by clicking search button")
    @Story("SearchPage loading")
    public void moveToSearchPageFromHomeTest() {
        SearchPage searchPage = Application
                .get()
                .loadHomePage()
                .moveToSearchPage();
        String expectedUrl = Application
                .get()
                .getApplicationSource()
                .getSearchPageUrl();
        Assert.assertEquals(searchPage.getCurrentUrl(), expectedUrl);

        logger.info("moveToSearchPageFromHomeTest - url - " + searchPage.getCurrentUrl());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description( "Test loads search page by URL")
    @Story("SearchPage loading")
    public void loadPageTest() {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage();
        String expectedUrl = Application
                .get()
                .getApplicationSource()
                .getSearchPageUrl();
        Assert.assertEquals(searchPage.getCurrentUrl(), expectedUrl);
    }

    @DataProvider
    public Object[][] extendedSearchProvider() {
        return new Object[][] {
                {"i", Categories.DESCTOPS, 11},
                {"Sony VAIO", Categories.ALL, 1}
        };
    }

    @Test(dataProvider = "extendedSearchProvider")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search by keyword and category")
    @Story("Extended search")
    public void extendedSearchTest(String keyWord, Categories category, int expectedQuantity) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        int actual = searchPage.countProductsFound();
        Assert.assertEquals(actual, expectedQuantity);
        Assert.assertTrue(searchPage.isTitleContainKeyword(keyWord));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Search by empty input field and category")
    @Story("Extended search")
    public void searchByEmptyFieldTest() {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch("", Categories.ALL);
        Assert.assertFalse(searchPage
                .getSearchResultsBlock()
                .isProductFound());
    }

    @DataProvider
    public Object[][] searchForSortingAndSwitchingProvider() {
        return new Object[][] {
                {"mac", Categories.ALL}
        };
    }

    @Test(dataProvider = "searchForSortingAndSwitchingProvider")
    @Severity(SeverityLevel.NORMAL)
    @Description("Search and sorting results by price ascending")
    @Story("Extended search")
    public void sortingByPriceAscTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        searchPage.sortProducts(SortingType.PRICE_ASC);
        Assert.assertTrue(searchPage.isPricesSortedByAsc());
    }

    @Test(dataProvider = "searchForSortingAndSwitchingProvider")
    @Severity(SeverityLevel.NORMAL)
    @Description("Search and sorting results by price descending")
    @Story("Extended search")
    public void sortingByPriceDescTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        searchPage.sortProducts(SortingType.PRICE_DESC);
        Assert.assertTrue(searchPage.isPricesSortedByDesc());
    }

    @Test(dataProvider = "searchForSortingAndSwitchingProvider")
    @Severity(SeverityLevel.NORMAL)
    @Description("Search and switching results into list view")
    @Story("Extended search")
    public void switchingToListViewTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        searchPage.displayProductsAsList();
        Assert.assertTrue(searchPage.isProductDisplayedAsList());
    }

    @Test(dataProvider = "searchForSortingAndSwitchingProvider")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Search and switching results into grid view")
    @Story("Extended search")
    public void switchingToGridViewTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        searchPage.displayProductsAsGrid();
        Assert.assertTrue(searchPage.isProductDisplayedAsGrid());
    }

    @DataProvider
    public Object[][] changeLimitsProvider() {
        return new Object[][] {
                {"i", Categories.ALL}
        };
    }

    @Test(dataProvider = "changeLimitsProvider")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Search and change results limit on page")
    @Story("Extended search")
    public void changeProductsLimitOnPageTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        Assert.assertEquals(searchPage.countProductsFound(),
                ProductsLimitOnPage.DEFAULT_LIMIT_15.toInt());
        searchPage.changeProductsLimitOnPage(ProductsLimitOnPage.LIMIT_18);
        Assert.assertEquals(searchPage.countProductsFound(),
                ProductsLimitOnPage.LIMIT_18.toInt());
    }

    @DataProvider
    public Object[][] paginationProvider() {
        return new Object[][] {
                {"i", Categories.ALL, "Showing 16 to 18 of 18 (2 Pages)"}
        };
    }

    @Test(dataProvider = "paginationProvider")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Search and move to another results page")
    @Story("Extended search")
    public void goToNextSearchResultsPageTest(String keyWord,
                                              Categories category,
                                              String expectedPageNumberDescription) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        Assert.assertEquals(searchPage.countProductsFound(),
                ProductsLimitOnPage.DEFAULT_LIMIT_15.toInt());
        searchPage.moveToNextSearchResultsPage();
        Assert.assertEquals(searchPage.getSearchResultsBlock()
                        .getPageNumberDescriptionText(),
                        expectedPageNumberDescription);
    }
}
