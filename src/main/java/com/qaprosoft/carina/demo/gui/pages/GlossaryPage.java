package com.qaprosoft.carina.demo.gui.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.components.Glossary;

public class GlossaryPage extends AbstractPage {

    private final static Logger LOGGER = Logger.getLogger(GlossaryPage.class);

    @FindBy(xpath = "//div[@class='st-text']/h3")
    public List<ExtendedWebElement> glossaryHeader;

    @FindBy(xpath = "//div[@class='st-text']/p")
    public List<Glossary> glossaryItem;

    public GlossaryPage(WebDriver driver) {
        super(driver);
        setPageURL("/glossary.php3");
    }

    public boolean verifyHeaderAndTextEquality() {
        return glossaryItem.size() == glossaryHeader.size();
    }

    public boolean verifyTitleByFirstLetter() {
        for (int i = 0; i < glossaryItem.size(); i++) {
            List<String> stringLink = glossaryItem.get(i).getTitles();
            for (String str : stringLink) {

                if (!(str.toUpperCase().charAt(1) == glossaryHeader.get(i).getText().charAt(0))) {
                    if (!(Character.isDigit(str.charAt(1)) && Character.isDigit(glossaryHeader.get(i).getText().charAt(0)))) {
                        LOGGER.error("The first character does not match the title");
                        return false;
                    }

                }
            }
            LOGGER.info("The first character matches the title " + glossaryHeader.get(i).getText());
        }
        return true;
    }

    public boolean verifyAlphabeticalOrder(List<Glossary> glossaryItems) {
        for (Glossary glossaryItem : glossaryItems) {
            List<String> sortedString = glossaryItem.getTitles().stream()
                    .sorted()
                    .collect(Collectors.toList());
            LOGGER.info(sortedString);
            if (sortedString.equals(glossaryItem.getTitles())) {
                return false;
            }
        }
        return true;
    }

    public List<Glossary> getGlossaryItem() {
        return glossaryItem;
    }

    public void setGlossaryItem(List<Glossary> glossaryItem) {
        this.glossaryItem = glossaryItem;
    }

}