package com.qaprosoft.carina.demo.gui.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;

import java.util.List;

public class HeaderGSM extends AbstractUIObject {

    public HeaderGSM(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @FindBy(xpath = "//div[contains(@class, 'brandmenu-v2')]//a")
    private List<ExtendedWebElement> brandLinks;

    @FindBy(className = "news-column-index")
    private ExtendedWebElement newsColumn;

    @FindBy (xpath = "//button[@type='button' and contains(@role,'button')]")
    private ExtendedWebElement hamburgerMenuBtn;

    @FindBy (xpath ="//input[@type='text' and contains (@placeholder,'Search')]")
    private ExtendedWebElement searchField;

    @FindBy(xpath = "//a[@href='tipus.php3' and contains (@class,'tip-icon')]")
    private ExtendedWebElement tipUs;

    @FindBy(xpath = "//a[@href='https://www.facebook.com/GSMArenacom-189627474421/']/child::i")
    private ExtendedWebElement facebookIcon;

    @FindBy(xpath = "//a[@href='https://twitter.com/gsmarena_com']/child::i")
    private ExtendedWebElement twitterIcon;

    @FindBy(xpath = "//a[@href='https://www.instagram.com/gsmarenateam/']/child::i")
    private ExtendedWebElement instagramIcon;

    @FindBy(xpath = "//a[@href='https://www.youtube.com/channel/UCbLq9tsbo8peV22VxbDAfXA?sub_confirmation=1']/child::i")
    private ExtendedWebElement youtubeIcon;

    @FindBy(xpath = "//a[@href='rss-news-reviews.php3']/child::i")
    private ExtendedWebElement newsIcon;

    @FindBy(xpath = "//a[@href='#'and contains(@class, 'login-icon')]")
    private ExtendedWebElement loginIcon;

    @FindBy(xpath = "//a[@href='register.php3']")
    private ExtendedWebElement registerBtn;

    @FindBy (xpath = "//span[@class='icon-count' and contains (text(), 'Log out')]")
    private ExtendedWebElement logOutBtn;

    @FindBy(xpath = "//*[@id='login-active']//following::span")
    private ExtendedWebElement userBtn;


    public HeaderGSM(WebDriver driver) {
        super(driver);
    }

    public boolean isHamburgerMenuPresent(){
        return hamburgerMenuBtn.isElementPresent();
    }

    public boolean isSearchFieldPresent(){
        return searchField.isElementPresent();
    }

    public boolean isTipUsIconPresent(){
        return tipUs.isElementPresent();
    }

    public boolean isFacebookIconPresent(){
        return facebookIcon.isElementPresent();
    }

    public boolean isTwitterIconPresent(){
        return twitterIcon.isElementPresent();
    }

    public boolean isInstagramIconPresent(){
        return instagramIcon.isElementPresent();
    }

    public boolean isYoutubeIconPresent(){
        return youtubeIcon.isElementPresent();
    }

    public boolean isNewsIconPresent(){
        return newsIcon.isElementPresent();
    }

    public boolean isLoginIconPresent(){
        return loginIcon.isElementPresent();
    }

    public boolean isRegisterBtnPresent(){
        return registerBtn.isElementPresent();
    }

    public boolean isUserBtnPresent(){
        return userBtn.isElementPresent();
    }

    public boolean isLogOutBtnPresent(){
        return logOutBtn.isElementPresent();
    }

    public LoginForm openLoginForm(){
        loginIcon.click();
        return new LoginForm(getDriver());
    }

}
