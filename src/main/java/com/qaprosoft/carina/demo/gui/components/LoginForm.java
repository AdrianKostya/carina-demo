package com.qaprosoft.carina.demo.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.pages.HomePage;
import com.qaprosoft.carina.demo.gui.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends AbstractPage {

    @FindBy(id = "email")
    private ExtendedWebElement emailField;

    @FindBy(id = "upass")
    private ExtendedWebElement passwordField;

    @FindBy(id = "nick-submit")
    private ExtendedWebElement loginBtnSubmit;

    @FindBy(className = "forgot")
    private ExtendedWebElement forgotBtn;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

   public boolean isPageOpened(){
        return emailField.isElementPresent();
   }

   public void inputEmail(String email){
        emailField.type(email);
   }

   public void inputPassword(String password){
        passwordField.type(password);
   }

   public LoginPage login(String email, String password){
        inputEmail(email);
        inputPassword(password);
        loginBtnSubmit.click();
        return new LoginPage(getDriver());
   }

}
