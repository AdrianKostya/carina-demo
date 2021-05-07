package com.qaprosoft.carina.demo.gui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.gui.pages.BrandModelsPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PhoneModelBlock extends AbstractUIObject {

    @FindBy(xpath = "//a[text()='%s']")
    private ExtendedWebElement phoneModel;

    public PhoneModelBlock(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public BrandModelsPage chosePhoneModel(String text){
        phoneModel.format(text).click();
        return new BrandModelsPage(getDriver());
    }

}
