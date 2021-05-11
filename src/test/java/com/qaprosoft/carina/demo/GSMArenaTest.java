package com.qaprosoft.carina.demo;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qaprosoft.carina.demo.gui.components.HamburgerMenu;
import com.qaprosoft.carina.demo.gui.components.HeaderGSM;
import com.qaprosoft.carina.demo.gui.components.LoginForm;
import com.qaprosoft.carina.demo.gui.components.NewsItem;
import com.qaprosoft.carina.demo.gui.components.PhoneFinderBtn;
import com.qaprosoft.carina.demo.gui.components.PhoneModelBlock;
import com.qaprosoft.carina.demo.gui.components.UserGSM;
import com.qaprosoft.carina.demo.gui.pages.ArticlePage;
import com.qaprosoft.carina.demo.gui.pages.BrandModelsPage;
import com.qaprosoft.carina.demo.gui.pages.GlossaryPage;
import com.qaprosoft.carina.demo.gui.pages.HomePage;
import com.qaprosoft.carina.demo.gui.pages.LoginPage;
import com.qaprosoft.carina.demo.gui.pages.ModelInfoPage;
import com.qaprosoft.carina.demo.gui.pages.NewsPage;
import com.qaprosoft.carina.demo.gui.pages.OpinionPage;
import com.qaprosoft.carina.demo.gui.pages.PhoneFinderPage;
import com.qaprosoft.carina.demo.gui.pages.ResultPage;
import com.qaprosoft.carina.core.foundation.dataprovider.annotations.XlsDataSourceParameters;
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

    @Test
    public void  verifyPhoneFinder(){
        String model = "Samsung";
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        PhoneFinderBtn phoneFinderBtn = new PhoneFinderBtn(getDriver());
        PhoneFinderPage phoneFinderPage =phoneFinderBtn.getPhoneFinderPage();
        String resultPhoneFinderPage = phoneFinderPage.getPhoneFinderSettings().selectBrand(model);
        ResultPage resultPage=phoneFinderPage.getPhoneFinderSettings().getResultPage();
        String resultPageNumber = resultPage.getResultNumber();
        Assert.assertEquals(resultPhoneFinderPage, resultPageNumber, "Numbers is different");
        Assert.assertTrue(resultPage.isAllModelHaveSearchedTitle(model), "Model don't have searched title");
        resultPage.getPrevPage();
        Assert.assertTrue(phoneFinderPage.isPageOpened());
    }

    @Test
    public void verifyOpinionsOnPhonePage(){
        LoginService loginService = new LoginService(getDriver());
        UserService userService = new UserService();
        UserGSM userGSM = userService.getUser();
        HomePage homePage= loginService.login(userGSM.getEmail(), userGSM.getPassword());
        PhoneModelBlock phoneModelBlock =homePage.getPhoneModelList();
        BrandModelsPage brandModelsPage =phoneModelBlock.chosePhoneModel("Apple");
        brandModelsPage.getPopularityBtn();
        ModelInfoPage modelInfoPage = brandModelsPage.getFirstElement(0);
        OpinionPage opinionPage = modelInfoPage.clickOpinionBtn();
        Assert.assertTrue(opinionPage.sortByBestRating().isRatingSortedByStream(), "List is not sorted by best rating");
        Assert.assertTrue(opinionPage.sortByNewestDate().isDateSortedNewestFirst(), "List is not sorted by newestDateFirst");
        Assert.assertTrue(opinionPage.sortByLatestDate().isDateSortedOldestFirst(),"List is not sorted by LatestDateFirst");
        Assert.assertTrue(opinionPage.sortByBestRating().isCommentRate(0), "Comment  cant  be rate");
        Assert.assertTrue(opinionPage.sortByBestRating().isCommentUnrate(0), "Comment  cant  be unrate");
    }
}

