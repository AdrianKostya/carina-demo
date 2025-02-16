package com.qaprosoft.carina.demo.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.qaprosoft.carina.core.gui.AbstractPage;

public abstract class LoginPageBase extends AbstractPage {

	public LoginPageBase(WebDriver driver) {
		super(driver);
	}

	public abstract void typeName(String name);

	public abstract void typePassword(String password);

	public abstract void selectMaleSex();

	public abstract void checkPrivacyPolicyCheckbox();

	public abstract CarinaDescriptionPageBase clickLoginBtn();

	public abstract boolean isLoginBtnActive();

	public abstract CarinaDescriptionPageBase login();

	public abstract boolean isNameInputFieldArePresent();

	public abstract boolean isPasswordInputFieldArePresent();

	public abstract boolean isMaleRadioBtnArePresent();

	public abstract boolean isFemaleRadioBtnArePresent();

	public abstract boolean isMaleGenderSelected();

	public abstract boolean isFemaleGenderSelected();

	public abstract String getPassword();

}
