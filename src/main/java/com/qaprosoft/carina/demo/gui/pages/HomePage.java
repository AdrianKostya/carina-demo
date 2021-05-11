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
package com.qaprosoft.carina.demo.gui.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.demo.gui.components.PhoneModelBlock;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.demo.gui.components.HeaderGSM;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.components.FooterMenu;
import com.qaprosoft.carina.demo.gui.components.WeValuePrivacyAd;


public class HomePage extends AbstractPage {
    private final static org.apache.log4j.Logger LOGGER = Logger.getLogger(HomePage.class);

    @FindBy(xpath ="//h4[contains(text(), 'Top 10 by daily interest')]")
    private ExtendedWebElement top10Text;

    @FindBy(id = "footmenu")
    private FooterMenu footerMenu;

    @FindBy (id = "//*[@id='header']")
    private HeaderGSM headerGSM;

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

    @FindBy(xpath = "//*[@id='body']/aside/div[1]/ul")
    private PhoneModelBlock phoneModelList;

    public HomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(newsColumn);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public FooterMenu getFooterMenu() {
        return footerMenu;
    }

    public HeaderGSM getHeaderGSM(){
        return headerGSM;
    }

    public BrandModelsPage selectBrand(String brand) {
        LOGGER.info("selecting '" + brand + "' brand...");
        for (ExtendedWebElement brandLink : brandLinks) {
            String currentBrand = brandLink.getText();
            LOGGER.info("currentBrand: " + currentBrand);
            if (brand.equalsIgnoreCase(currentBrand)) {
                brandLink.click();
                return new BrandModelsPage(driver);
            }
        }
        throw new RuntimeException("Unable to open brand: " + brand);
    }
    
    public WeValuePrivacyAd getWeValuePrivacyAd() {
    	return new WeValuePrivacyAd(driver);
    }

    public boolean isPageOpened(){
        return top10Text.isElementPresent();
    }

    public PhoneModelBlock getPhoneModelList(){
        return phoneModelList;
    }

}
