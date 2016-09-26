package com.mdstech.tadsl.pages;

import ch.lambdaj.function.convert.Converter;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.WebElementFacade;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

import static ch.lambdaj.Lambda.convert;

@DefaultUrl("http://google.com")
public class DictionaryPage extends PageObject {

    @FindBy(name="q")
    private WebElementFacade searchTerms;

    @FindBy(name="btnG")
    private WebElementFacade lookupButton;
    
    private String keyword;

    public void enter_keywords(String keyword) {
    		this.keyword = keyword;
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
//        lookupButton.click();
        searchTerms.submit();
	    new WebDriverWait(this.getDriver(), 10).until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver arg0) {
				return arg0.getTitle().toLowerCase().startsWith(keyword);
			}
		});
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.id("ires")).find(By.className("rc"));
        List<WebElement> results = definitionList.findElements(By.tagName("h3"));
        return convert(results, toStrings());
    }

    private Converter<WebElement, String> toStrings() {
        return new Converter<WebElement, String>() {
            public String convert(WebElement from) {
                return from.getText();
            }
        };
    }
}