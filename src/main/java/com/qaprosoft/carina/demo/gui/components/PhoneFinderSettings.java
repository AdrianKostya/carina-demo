package com.qaprosoft.carina.demo.gui.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.gui.pages.ResultPage;

public class PhoneFinderSettings extends AbstractUIObject {

    @FindBy(xpath = "//button[contains(@class, 'btn')][1]")
    private ExtendedWebElement brandBtn;

    @FindBy(xpath = "//input[contains(@class, 'form-control')][1]")
    private ExtendedWebElement inputBrandField;

    @FindBy(xpath = "//span[@class='text' and contains(text(), '%s')]")
    private ExtendedWebElement brandName;

    @FindBy(xpath = "//span[@class=\"pf-results\"]")
    private ExtendedWebElement resultNumberButton;

    @FindBy(xpath = "//input[contains(@value, \"Show\")]")
    private ExtendedWebElement searchBtn;

    public PhoneFinderSettings(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String selectBrand(String text){
        brandBtn.click();
        inputBrandField.type(text);
        brandName.format(text).click();
        pause(1);
        String resultNumber = resultNumberButton.getText();
        System.out.println("results Number ! : "+resultNumber);
        return resultNumber;
    }

    public ResultPage getResultPage(){
        searchBtn.click();
        return new ResultPage(getDriver());
    }

}
