package com.qaprosoft.carina.demo.gui.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;

public class OpinionSortingWindow extends AbstractUIObject {
    private final static Logger LOGGER = Logger.getLogger(OpinionSortingWindow.class);

    @FindBy(xpath = "//span[@class='thumbs-score']")
    private List<ExtendedWebElement> rating;

    @FindBy(xpath = "//a[@class='voting-link vote-up' ][1]")
    private List<ExtendedWebElement> rateUpArrow;

    @FindBy(xpath = "//a[contains(@class, 'voting-link vote-down')]")
    private List <ExtendedWebElement> rateDown;

    @FindBy(xpath = "//time")
    private List<ExtendedWebElement> time;

    public OpinionSortingWindow(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }


    public boolean isRatingSorted() {
        boolean currentValue = true, globalValue = true;
        if (!rating.isEmpty()) {
            for (int i = 0; i < rating.size() - 1; i++) {
                int firstRating = Integer.parseInt(rating.get(i).getText());
                int nextRating = Integer.parseInt(rating.get(i + 1).getText());
                LOGGER.info("First(Heigh) rating"+ firstRating+ "     "+ "NextRating(Low)"+nextRating);
                if (firstRating < nextRating) {
                    currentValue = false;
                }
                globalValue = currentValue;
            }
        }
        return globalValue;
    }

    public boolean isRatingSortedByStream(){
        boolean currentValue = true, globalValue = true;
        if (!rating.isEmpty()) {
            List<String> ratingStringList = new ArrayList<>();
            for (ExtendedWebElement rating : rating) {
                ratingStringList.add(rating.getText());
            }
            List<String> sortedList = ratingStringList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

            System.out.println("Rated string List : "+ratingStringList);
            System.out.println("Sorted List reversed : "+sortedList);
            if (!ratingStringList.equals(sortedList)) {
                currentValue = false;
            }
            globalValue = currentValue;
        }
        return globalValue;
    }

  public boolean isDateSortedNewestFirst() {
        boolean currentValue = true, globalValue = true;
        if (!time.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            for (int i = 0; i < time.size() - 1; i++) {
                String timeStr = time.get(i).getText();
                String timeStr2 = time.get(i + 1).getText();
                Date date = null;
                Date date2 = null;
                try {
                    date = simpleDateFormat.parse(timeStr);
                    date2 = simpleDateFormat.parse(timeStr2);
                    int result = date.compareTo(date2);
                    if (result == -1) {
                        currentValue = false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            globalValue = currentValue;
        }
        return globalValue;
    }

    public boolean isDateSortedOldestFirst() {
        boolean currentValue = true, globalValue = true;
        if (!time.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            for (int i = 0; i < time.size() - 1; i++) {
                String timeStr = time.get(i).getText();
                String timeStr2 = time.get(i + 1).getText();
                Date date = null;
                Date date2 = null;
                try {
                    date = simpleDateFormat.parse(timeStr);
                    date2 = simpleDateFormat.parse(timeStr2);
                    int result = date.compareTo(date2);
                    if (result == 1) {
                        currentValue = false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            globalValue = currentValue;
        }
        return globalValue;
    }

    public boolean isCommentRate(int index) {
        int initialRating = Integer.parseInt(rating.get(index).getText());
        rateUpArrow.get(index).click();
        int secondRating = Integer.parseInt(rating.get(index).getText());
        if (!rating.isEmpty()) {
            if (!(secondRating ==initialRating+1)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isCommentUnrate(int index) {
        boolean currentValue = true, globalValue = true;
        int initialRating = Integer.parseInt(rating.get(index).getText());
        rateDown.get(index).click();
        int secondRating = Integer.parseInt(rating.get(index).getText());
        if (!rating.isEmpty()) {
            if (!(initialRating - secondRating==1)) {
                currentValue = false;
            }
            globalValue = currentValue;
        }
        return globalValue;
    }

}
