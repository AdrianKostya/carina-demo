package com.qaprosoft.carina.demo.mobile.gui.pages.ios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.demo.constant.ProjectConstant;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType.Type;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.annotations.Predicate;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.*;

@DeviceType(pageType = Type.IOS_PHONE, parentClass = CarinaDescriptionPageBase.class)
public class IOSCarinaDescriptionPage extends CarinaDescriptionPageBase {

    @FindBy(xpath = "name = 'CARINA' AND type = 'XCUIElementTypeStaticText'")
    @Predicate
    private ExtendedWebElement webViewTitle;

    public IOSCarinaDescriptionPage(WebDriver driver) {
        super(driver);
    }

    public  void clickOnBurgerBtn(){
        throw new UnsupportedOperationException(ProjectConstant.UNIMPLEMENTED_FOR_IOS);
    }
    @Override
    public WebViewPageBase navigateToWebViewPage() {
        throw new UnsupportedOperationException(ProjectConstant.UNIMPLEMENTED_FOR_IOS);
    }

    @Override
    public ChartsPageBase navigateToChartsPage() {
        throw new UnsupportedOperationException(ProjectConstant.UNIMPLEMENTED_FOR_IOS);
    }

    @Override
    public MapsPageBase navigateToMapPage() {
        throw new UnsupportedOperationException(ProjectConstant.UNIMPLEMENTED_FOR_IOS);
    }

    @Override
    public UIElementsPageBase navigateToUIElementsPage() {
        throw new UnsupportedOperationException(ProjectConstant.UNIMPLEMENTED_FOR_IOS);
    }

    @Override
    public boolean isPageOpened() {
        return webViewTitle.isElementPresent();
    }

}
