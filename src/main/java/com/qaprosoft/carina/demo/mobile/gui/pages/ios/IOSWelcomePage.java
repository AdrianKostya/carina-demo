package com.qaprosoft.carina.demo.mobile.gui.pages.ios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType.Type;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.annotations.ClassChain;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.annotations.Predicate;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WelcomePageBase;

@DeviceType(pageType = Type.IOS_PHONE, parentClass = WelcomePageBase.class)
public class IOSWelcomePage extends WelcomePageBase {

    @FindBy(xpath = "name = 'Welcome to Carina World!'")
    @Predicate
    private ExtendedWebElement title;

    @FindBy(xpath = "**/XCUIElementTypeButton[`name == 'NEXT'`]")
    @ClassChain
    private ExtendedWebElement nextBtn;

    public IOSWelcomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent();
    }

    @Override
    public LoginPageBase clickNextBtn() {
        nextBtn.click();
        return initPage(getDriver(), LoginPageBase.class);
    }

}
