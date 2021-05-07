package com.qaprosoft.carina.demo.gui.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;

public class OpinionSortingWindow extends AbstractUIObject {

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
        if (!rating.isEmpty()) {
            for (int i = 0; i < rating.size() - 1; i++) {
                int previousRating = Integer.parseInt(rating.get(i).getText());
                int nextRating = Integer.parseInt(rating.get(i + 1).getText());
                if (previousRating < nextRating) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isRatingSortedByStream(){
        if (!rating.isEmpty()) {
            List<String> ratingStringList = new ArrayList<>();
            for (ExtendedWebElement rating : rating) {
                ratingStringList.add(rating.getText());
            }
            List<String> sortedList = ratingStringList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            if (!ratingStringList.equals(sortedList)) {
                return false;
            }
            return true;
        }
        return false;
    }

  public boolean isDateSortedNewestFirst() {
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
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    public boolean isDateSortedOldestFirst() {
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
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
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
        int initialRating = Integer.parseInt(rating.get(index).getText());
        rateDown.get(index).click();
        int secondRating = Integer.parseInt(rating.get(index).getText());
        if (!rating.isEmpty()) {
            if (!(initialRating - secondRating==1)) {
                return false;
            }
            return true;
        }
        return false;
    }

}
