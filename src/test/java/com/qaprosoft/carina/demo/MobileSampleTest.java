package com.qaprosoft.carina.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qaprosoft.carina.demo.mobile.gui.pages.common.CarinaDescriptionPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.ContactUsPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.MapsPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.UIElementsPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WebViewPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.utils.MobileContextUtils;
import com.qaprosoft.carina.demo.utils.MobileContextUtils.View;

public class MobileSampleTest extends AbstractTest implements IMobileUtils {

    @Test(description = "JIRA#DEMO-0011")
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testLoginUser() {
        String username = "Test user";
        String password = RandomStringUtils.randomAlphabetic(10);
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page isn't opened");
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        Assert.assertFalse(loginPage.isLoginBtnActive(), "Login button is active when it should be disabled");
        loginPage.typeName(username);
        loginPage.typePassword(password);
        loginPage.selectMaleSex();
        loginPage.checkPrivacyPolicyCheckbox();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.clickLoginBtn();
        Assert.assertTrue(carinaDescriptionPage.isPageOpened(), "Carina description page isn't opened");
    }

    @Test(description = "JIRA#DEMO-0011")
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testWebView() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        loginPage.login();
        WebViewPageBase webViewPageBase = initPage(getDriver(), WebViewPageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(View.WEB);
        ContactUsPageBase contactUsPage = webViewPageBase.goToContactUsPage();
        contactUsPage.typeName("John Doe");
        contactUsPage.typeEmail("some@email.com");
        contactUsPage.typeQuestion("This is a message");
        //TODO: [VD] move page driver related action outside from test class!
        hideKeyboard();
        contactUsPage.submit();
        Assert.assertTrue(contactUsPage.isSuccessMessagePresent() || contactUsPage.isRecaptchaPresent(),
                "message was not sent or captcha was not displayed");
    }

    @Test(description = "JIRA#DEMO-0011")
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "acceptance"})
    public void testUIElements() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.login();
        UIElementsPageBase uiElements = carinaDescriptionPage.navigateToUIElementsPage();
        final String text = "some text";
        final String date = "22/10/2018";
        final String email = "some@email.com";
        uiElements.typeText(text);
        Assert.assertEquals(uiElements.getText(), text, "Text was not typed");
        uiElements.typeDate(date);
        Assert.assertEquals(uiElements.getDate(), date, "Date was not typed");
        uiElements.typeEmail(email);
        Assert.assertEquals(uiElements.getEmail(), email, "Email was not typed");
        uiElements.swipeToFemaleRadioButton();
        uiElements.checkCopy();
        Assert.assertTrue(uiElements.isCopyChecked(), "Copy checkbox was not checked");
        uiElements.clickOnFemaleRadioButton();
        Assert.assertTrue(uiElements.isFemaleRadioButtonSelected(), "Female radio button was not selected!");
        uiElements.clickOnOtherRadioButton();
        Assert.assertTrue(uiElements.isOthersRadioButtonSelected(), "Others radio button was not selected!");
    }

    @Test(description = "JIRA#DEMO-0011")
    @MethodOwner(owner = "akostya")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void verifyLoginPage() {
        SoftAssert softAssert = new SoftAssert();
        WelcomePageBase welcomePageBase = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePageBase.isPageOpened(), "Welcome page is not opened");
        LoginPageBase loginPageBase = welcomePageBase.clickNextBtn();
        softAssert.assertTrue(loginPageBase.isNameInputFieldArePresent(), "Name input field isn't present");
        softAssert.assertTrue(loginPageBase.isPasswordInputFieldArePresent(), "Password input field isn't present");
        softAssert.assertTrue(loginPageBase.isMaleRadioBtnArePresent(), "Male Btn aren't present");
        softAssert.assertTrue(loginPageBase.isFemaleRadioBtnArePresent(), "Female Btn aren't present");
        softAssert.assertFalse(loginPageBase.isLoginBtnActive(), "Login is active before input fields");
        String userName = "Test user";
        loginPageBase.typeName(userName);
        softAssert.assertEquals(loginPageBase.getName(), userName, "Typed name is different");
        String password = RandomStringUtils.randomAlphabetic(10);
        loginPageBase.typePassword(password);
        softAssert.assertEquals(loginPageBase.getPassword(), password, "Password is different");
        loginPageBase.selectMaleSex();
        softAssert.assertTrue(loginPageBase.isMaleGenderSelected(), "Male sex is not selected");
        softAssert.assertFalse(loginPageBase.isLoginBtnActive(), "Login is active before input fields");
        loginPageBase.checkPrivacyPolicyCheckbox();
        CarinaDescriptionPageBase carinaDescriptionPageBase = loginPageBase.clickLoginBtn();
        Assert.assertTrue(carinaDescriptionPageBase.isPageOpened(), "Carina description page is not opened");
        softAssert.assertAll();
    }

    @Test(description = "JIRA#DEMO-0011")
    @MethodOwner(owner = "akostya")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void verifyMapFeature() {
        SoftAssert softAssert = new SoftAssert();
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.login();
        carinaDescriptionPage.isPageOpened();
        MapsPageBase mapsPageBase = carinaDescriptionPage.navigateToMapPage();
        softAssert.assertTrue(mapsPageBase.isZoomInPresent(), "Zoom in is not present");
        softAssert.assertTrue(mapsPageBase.isZoomOutPresent(), "Zoom out is not present");
        softAssert.assertTrue(mapsPageBase.isZoomInAboveZoomOut(), "Zoom in is not above zoom out");
        Assert.assertTrue(carinaDescriptionPage.isPageOpened(), "Carina description page is not opened");
        softAssert.assertAll();
    }

}
