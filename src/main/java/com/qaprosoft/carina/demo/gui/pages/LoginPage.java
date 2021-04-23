package com.qaprosoft.carina.demo.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//p[contains(text(), \"Reason: User record not found.\")]")
    private ExtendedWebElement loginFailed;

    @FindBy(xpath = "//p[contains(text(), \"Reason: Wrong password.\")]")
    private ExtendedWebElement wrongPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isWrongPasswordTitlePresent(){
        return wrongPassword.isElementPresent();
    }

    public boolean isWrongEmailTitlePresent(){
        return  loginFailed.isElementPresent();
    }

}
