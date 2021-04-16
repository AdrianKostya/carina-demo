package com.qaprosoft.carina.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.foundation.webdriver.locator.ExtendedFindBy;

public class MapsPageBase extends AbstractPage {

    @ExtendedFindBy(androidUIAutomator ="new UiSelector().description(\"Zoom in\")")
    private ExtendedWebElement zoomIn;

    @ExtendedFindBy(androidUIAutomator ="new UiSelector().description(\"Zoom out\")")
    private ExtendedWebElement zoomOut;

    public MapsPageBase(WebDriver driver) {
        super(driver);
    }

    public boolean isZoomInPresent(){
        return zoomIn.isElementPresent();
    }

    public boolean isZoomOutPresent(){
        return zoomOut.isElementPresent();
    }

    public boolean isZoomInAboveZoomOut () {
        return (zoomIn.getLocation().getY()) < (zoomOut.getLocation().getY());
    }

}
