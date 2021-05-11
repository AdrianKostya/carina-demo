package com.qaprosoft.carina.demo.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;

import java.util.List;

public class ResultPage extends AbstractPage {

    @FindBy(xpath = "//a[contains(@href,'search.php3?sMakers=9')]")
    private ExtendedWebElement getPreviousPageBtn;

    @FindBy(xpath = "//strong")
    private List<ExtendedWebElement> modelTitles;

    @FindBy(xpath = "//div[@class='st-text']//following::b")
    private ExtendedWebElement resultNumber;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public String getResultNumber(){
        String result = resultNumber.getText()+" results";
        return result;
    }

    public boolean isAllModelHaveSearchedTitle(String text){
        if (!modelTitles.isEmpty()) {
            for (ExtendedWebElement modelTitle : modelTitles) {
                if (!modelTitle.getText().contains(text)) {
                    return false;
                }
                System.out.println("Searched text is :"+modelTitle);
            }
            return true;
        }
        return false;
    }

    public void getPrevPage(){
        getPreviousPageBtn.click();
    }
}
