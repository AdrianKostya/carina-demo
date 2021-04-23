package com.qaprosoft.carina.demo.gui.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.components.HeaderGSM;
import com.qaprosoft.carina.demo.gui.components.LoginForm;
import com.qaprosoft.carina.demo.gui.pages.HomePage;

public class LoginService extends AbstractPage {

    @FindBy(id = "login-active")
    private ExtendedWebElement loginBtn;

    public LoginService(WebDriver driver) {
        super(driver);
    }

    public HomePage login(String email, String password){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        softAssert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");
        LoginForm loginForm = homePage.getHeaderGSM().openLoginForm();
        softAssert.assertTrue(loginForm.isPageOpened(), "Login form is not opened");
        loginForm.login(email, password);
        HeaderGSM headerGSM = new HeaderGSM(getDriver());
        softAssert.assertTrue(headerGSM.isUserBtnPresent(), "User btn is not present");
        softAssert.assertTrue(headerGSM.isLogOutBtnPresent(), "Log out btn is not present");
        return new HomePage(getDriver());
    }

}
