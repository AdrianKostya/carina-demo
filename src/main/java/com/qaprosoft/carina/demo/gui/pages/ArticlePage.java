package com.qaprosoft.carina.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ArticlePage extends AbstractPage {
    private final static Logger LOGGER = Logger.getLogger(ArticlePage.class);

    @FindBy(xpath = "//h1[@class='article-info-name']")
    private ExtendedWebElement titleItem;

    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    public String getArticleTitle(){
        return titleItem.getText();
    }

}
