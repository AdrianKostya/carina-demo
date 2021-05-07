package com.qaprosoft.carina.demo.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.gui.components.PhoneFinderSettings;
import com.qaprosoft.carina.core.gui.AbstractPage;

public class PhoneFinderPage extends AbstractPage {

    @FindBy(xpath = "//h1[@class='article-info-name']")
    private ExtendedWebElement phoneFinderTitle;

    @FindBy (xpath = "//form[@action='results.php3']")
    private PhoneFinderSettings phoneFinderSettings;

    public PhoneFinderPage(WebDriver driver) {
        super(driver);
        setPageURL("/search.php3?");
    }

    public PhoneFinderSettings getPhoneFinderSettings(){
        return phoneFinderSettings;
    }

    public boolean isPageOpened(){
        return phoneFinderTitle.isElementPresent();
    }

}

