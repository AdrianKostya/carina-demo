package com.qaprosoft.carina.demo;

import java.util.HashMap;
import java.util.List;

import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
import com.qaprosoft.carina.demo.gui.components.*;
import com.qaprosoft.carina.demo.gui.pages.*;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.demo.gui.services.LoginService;
import com.qaprosoft.carina.demo.gui.services.UserService;

public class GSMArenaTest extends AbstractTest {

    @Test
    public void VerifyHeaderComponents(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        HeaderGSM headerGSM = new HeaderGSM(getDriver());
        headerGSM.validateHeaderGsmElements(softAssert);
        softAssert.assertAll();
    }

    @Test
    public void verifyUserLogin(){
        UserService userService = new UserService();
        UserGSM userGSM = userService.getUser();
        LoginService loginService = new LoginService(getDriver());
        HomePage homePage = loginService.login(userGSM.getEmail(), userGSM.getPassword());
    }

    @Test
    public void loginWithWrongEmail(){
        UserService userService = new UserService();
        UserGSM userGSM = userService.getUser();
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.isPageOpened();
        LoginForm loginForm = homePage.getHeaderGSM().openLoginForm();
        Assert.assertTrue(loginForm.isPageOpened());
        LoginPage loginPage =loginForm.login("s9rowa@mail.r", userGSM.getPassword());
        Assert.assertTrue(loginPage.isWrongEmailTitlePresent(), "Wrong login title is not present");
    }

    @Test
    public void loginWithWrongPassword(){
        UserService userService = new UserService();
        UserGSM userGSM = userService.getUser();
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        LoginForm loginForm= homePage.getHeaderGSM().openLoginForm();
        LoginPage loginPage = loginForm.login(userGSM.getEmail(), "asdfasd");
        Assert.assertTrue(loginPage.isWrongPasswordTitlePresent(),"Wrong password title is not present");
    }

    @Test
    public void verifyArticleName(){
        LoginService loginService = new LoginService(getDriver());
        UserService userService = new UserService();
        UserGSM userGSM = userService.getUser();
        loginService.login(userGSM.getEmail(), userGSM.getPassword());
        HomePage homePage = new HomePage(getDriver());
        NewsPage newsPage = homePage.getFooterMenu().openNewsPage();
        Assert.assertTrue(newsPage.isPageOpened());
        String newsItemTitle = newsPage.getNewsItem(0).getTitle();
        ArticlePage articlePage = newsPage.getNewsItem(0).clickOnItem();
        Assert.assertEquals(newsItemTitle, articlePage.getArticleTitle(), "Titles is not the same");
    }

    @Test(dataProvider = "dataProvider")
    @XlsDataSourceParameters(path = "xls/phone.xlsx", sheet = "phoneSheet", dsUid = "TUID")
    public void verifySearchingProcessWithXLS(HashMap<String, String> args){
        final String itemName = args.get("brand");
        LoginService loginService = new LoginService(getDriver());
        UserService userService = new UserService();
        UserGSM userGSM = userService.getUser();
        HomePage homePage= loginService.login(userGSM.getEmail(), userGSM.getPassword());
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        NewsPage newsPage = homePage.getFooterMenu().openNewsPage();
        Assert.assertTrue(newsPage.isPageOpened());
        List<NewsItem> newsItems= newsPage.searchNews(itemName);
        for (NewsItem news:newsItems){
            System.out.println("String NEWS: "+news.readTitle());
            Assert.assertTrue(StringUtils.containsIgnoreCase(news.readTitle(),  itemName), "titles not the same");
        }
    }

    @Test
    @Parameters({ "phoneSearch" })
    public void verifySearchingProcess(String search){
        LoginService loginService = new LoginService(getDriver());
        UserService userService = new UserService();
        UserGSM userGSM = userService.getUser();
        HomePage homePage= loginService.login(userGSM.getEmail(), userGSM.getPassword());
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        NewsPage newsPage = homePage.getFooterMenu().openNewsPage();
        Assert.assertTrue(newsPage.isPageOpened());
        List<NewsItem> newsItems= newsPage.searchNews(search);
        for (NewsItem news:newsItems){
            System.out.println("String NEWS: "+news.readTitle());
            Assert.assertTrue(StringUtils.containsIgnoreCase(news.readTitle(),  search), "titles not the same");
        }
    }

    @Test(description = "JIRA#AUTO-0008")
    public void testVerifyGlossaryFirstLetterInTitle() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        GlossaryPage glossaryPage = homePage.getFooterMenu().openGlossaryPage();
        Assert.assertTrue(glossaryPage.isPageOpened(), "Glossary Page isn't opened");
        Assert.assertTrue(glossaryPage.verifyHeaderAndTextEquality(), "Elements don't match");
        Assert.assertTrue(glossaryPage.verifyTitleByFirstLetter(), "Not alphabet sort");
    }

    @Test(description = "JIRA#AUTO-0008")
    public void testVerifyGlossaryTextByAlphabets() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        GlossaryPage glossaryPage = homePage.getFooterMenu().openGlossaryPage();
        Assert.assertTrue(glossaryPage.isPageOpened(), "Glossary Page isn't opened");
        Assert.assertTrue(glossaryPage.verifyHeaderAndTextEquality(), "Header and text items don't match");
        Assert.assertTrue(glossaryPage.verifyAlphabeticalOrder(glossaryPage.glossaryItem));
    }

    @Test
    public void verifyHamburgerPageOpening(){
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        HamburgerMenu hamburgerMenu = homePage.getHeaderGSM().getHamburgerMenu();
        Assert.assertTrue(hamburgerMenu.openHomePage().isPageOpened(), "HomePage is not opened");
        Assert.assertTrue(hamburgerMenu.openNewsPage().isPageOpened(), "NewsPage is not opened");
        Assert.assertTrue(hamburgerMenu.openReviewsPage().isPageOpened(), "ReviewsPage is not opened");
        Assert.assertTrue(hamburgerMenu.openVideosPage().isPageOpened(), "Videos is not opened");
        Assert.assertTrue(hamburgerMenu.openFeaturedPage().isPageOpened(), "FeaturedPage is not opened");
        Assert.assertTrue(hamburgerMenu.openPhoneFinderPage().isPageOpened(), "PhoneFinderPage is not opened");
        Assert.assertTrue(hamburgerMenu.openDealsPage().isPageOpened(), "DealsPage is not opened");
        Assert.assertTrue(hamburgerMenu.openToolsPage().isPageOpened(), "ToolsPage is not opened");
        Assert.assertTrue(hamburgerMenu.openCoveragePage().isPageOpened(), "CoveragePage is not opened");
        Assert.assertTrue(hamburgerMenu.openContactPage().isPageOpened(), "ContactPage is not opened");
    }

}
