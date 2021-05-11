package com.qaprosoft.carina.demo.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.components.OpinionSortingWindow;

public class OpinionPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='user-thread']")
    private OpinionSortingWindow opinionSortingWindow;

    @FindBy(xpath = "//*[@id='sort-comments']")
    private ExtendedWebElement sortByButton;

    @FindBy(xpath = "//*[text()='Best rating']")
    private ExtendedWebElement bestRating;

    @FindBy(xpath = "//*[text()='Newest first']")
    private ExtendedWebElement newestDate;

    @FindBy(xpath = "//*[text()='Oldest first']")
    private ExtendedWebElement latestDate;

    public OpinionPage(WebDriver driver) {
        super(driver);
    }

    public OpinionSortingWindow sortByBestRating(){
        sortByButton.click();
        bestRating.click();
        return opinionSortingWindow;
    }

    public OpinionSortingWindow sortByNewestDate(){
        sortByButton.click();
        newestDate.click();
        return opinionSortingWindow;
    }

    public OpinionSortingWindow sortByLatestDate(){
        sortByButton.click();
        latestDate.click();
        return opinionSortingWindow;
    }

}

