package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.crypto.CryptoConsole;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.gui.components.NewsItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.demo.gui.components.HeaderGSM;
import com.qaprosoft.carina.demo.gui.components.LoginForm;
import com.qaprosoft.carina.demo.gui.components.UserGSM;
import com.qaprosoft.carina.demo.gui.pages.ArticlePage;
import com.qaprosoft.carina.demo.gui.pages.HomePage;
import com.qaprosoft.carina.demo.gui.pages.LoginPage;
import com.qaprosoft.carina.demo.gui.pages.NewsPage;
import com.qaprosoft.carina.demo.gui.services.LoginService;
import com.qaprosoft.carina.demo.gui.services.UserService;

import java.util.List;

public class GSMArenaTest extends AbstractTest {

    @Test
    public void VerifyHeaderComponents(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        HeaderGSM headerGSM = new HeaderGSM(getDriver());
        softAssert.assertTrue(headerGSM.isHamburgerMenuPresent(), "Hamburger menu is not present");
        softAssert.assertTrue(headerGSM.isSearchFieldPresent(), "Search field is not present");
        softAssert.assertTrue(headerGSM.isTipUsIconPresent(),"Tip us icon is not present");
        softAssert.assertTrue(headerGSM.isFacebookIconPresent(), "Facebook icon is not present");
        softAssert.assertTrue(headerGSM.isTwitterIconPresent(), "Twitter icon is not present");
        softAssert.assertTrue(headerGSM.isInstagramIconPresent(), "Instagram icon is not present");
        softAssert.assertTrue(headerGSM.isYoutubeIconPresent(), "Youtube icon is not present");
        softAssert.assertTrue(headerGSM.isNewsIconPresent(), "News icon is not present");
        softAssert.assertTrue(headerGSM.isLoginIconPresent(), "Login icon is not present");
        softAssert.assertTrue(headerGSM.isRegisterBtnPresent(), "Register btn is not present");
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

    @Test
    public void verifySearchingProcess(){
        LoginService loginService = new LoginService(getDriver());
        UserService userService = new UserService();
        UserGSM userGSM = userService.getUser();
        HomePage homePage= loginService.login(userGSM.getEmail(), userGSM.getPassword());
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        NewsPage newsPage = homePage.getFooterMenu().openNewsPage();
        Assert.assertTrue(newsPage.isPageOpened());
        String itemName = "iphone";
        List<NewsItem> newsItems= newsPage.searchNews(itemName);
        for (NewsItem news:newsItems){
            System.out.println("String NEWS: "+news.readTitle());
            Assert.assertTrue(StringUtils.containsIgnoreCase(news.readTitle(),  itemName), "titles not the same");
        }
    }

}
