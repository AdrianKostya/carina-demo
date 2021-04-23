package com.qaprosoft.carina.demo.gui.components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.pages.LoginPage;


public class LoginForm extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(LoginForm.class);

    @FindBy(id = "email")
    private ExtendedWebElement emailField;

    @FindBy(id = "upass")
    private ExtendedWebElement passwordField;

    @FindBy(id = "nick-submit")
    private ExtendedWebElement submitBtn;

    @FindBy(className = "forgot")
    private ExtendedWebElement forgotBtn;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

   public boolean isPageOpened(){
        return emailField.isElementPresent();
   }

   public void typeEmail(String email){
        emailField.type(email);
   }

   public void typePassword(String password){
        passwordField.type(password);
   }

   public LoginPage login(String email, String password){
       typeEmail(email);
        LOGGER.info("email is inputted");
       typePassword(password);
        LOGGER.info("password is inputted");
        submitBtn.click();
        return new LoginPage(getDriver());
   }

}
