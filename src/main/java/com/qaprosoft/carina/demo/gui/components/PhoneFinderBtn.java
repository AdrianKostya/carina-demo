package com.qaprosoft.carina.demo.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.gui.pages.PhoneFinderPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PhoneFinderBtn extends AbstractUIObject {

    @FindBy(xpath = "//p[@class='pad'][1]")
    private ExtendedWebElement finderButton;

    public PhoneFinderBtn(WebDriver driver) {
        super(driver);
    }


    public PhoneFinderPage getPhoneFinderPage(){
        finderButton.click();
        return new PhoneFinderPage(getDriver());
    }



}
