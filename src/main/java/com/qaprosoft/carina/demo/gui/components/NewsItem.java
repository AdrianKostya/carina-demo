/*
 * Copyright 2013-2021 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qaprosoft.carina.demo.gui.components;

import com.qaprosoft.carina.demo.gui.pages.ArticlePage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.testng.Assert;

import java.util.List;

public class NewsItem extends AbstractUIObject {
    public static final Logger LOGGER = Logger.getLogger(NewsItem.class);

    @FindBy(xpath="./a")
    public ExtendedWebElement titleLink;

    @FindBy(xpath = "//div[@class=\"news-item\"]//following::h3")
    private List<ExtendedWebElement> newsTitleList;
    
    public NewsItem(WebDriver driver, SearchContext sc) {
        super(driver, sc);
    }
    
    public String readTitle() {
        return titleLink.getText();
    }

    public String readTitleByIndex(int index){
        return newsTitleList.get(index).getText();
    }

    public boolean isAllItemsHaveSearchedWord(String item) {
        if (!newsTitleList.isEmpty()) {
            for (ExtendedWebElement newsTitle : newsTitleList) {
                String newsTitle1 = newsTitle.getText();
                if (!newsTitle1.contains(item)) {
                    LOGGER.info("News titles is not contains any"+item);
                    return false;
                }
            }
            return true;
        }
        LOGGER.info("News title list in EMPTY !");
        return false;
    }

    public ArticlePage clickOnItem(){
        newsTitleList.get(1).click();
        return new ArticlePage(getDriver());
    }


}
