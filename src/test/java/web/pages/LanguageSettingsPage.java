package web.pages;

import org.openqa.selenium.By;

import web.base.PageObject;
import web.utils.Logger;

/**
 * LanguageSettingsPage
 * 
 */
public class LanguageSettingsPage extends PageObject {

	final By saveChangesButton = By.xpath("//*[@id='icp-btn-save']");
	final By languageLabel = By.xpath("//*[@id='customer-preferences']//form//div[@data-a-input-name='LOP']/label/span");
    
    // Constructor
    public LanguageSettingsPage() {
    	super();
    }
    
 
    /**
   	 * This method is intended to click a prefer language by language country code
   	 * 
     * @param languageCountryCode the language country code (e.g. EN)
   	 */
    public void clickPreferLanguage(final String languageCountryCode) {
    	Logger.logInfo("Click prefer language '" + languageCountryCode + "' on Language Settings page");
    	selectListItemContainsText(languageLabel, languageCountryCode);
    	
    }

   
    /**
   	 * This method is intended to click Save Changes button on Language Settings page
     *  
   	 */
    public void clickSaveChangesButton() {  	
		Logger.logInfo("Click Save Changes button on Language Settings page");
		clickUsingActions(saveChangesButton);      
    	
    }
    
    /**
   	 * This method is intended to select a prefer language by language country code then save changes
     * 
     * @param languageCountryCode the language country code (e.g. EN)
   	 */
    public void selectAndSavePreferLanguage(final String languageCountryCode){
    	clickPreferLanguage(languageCountryCode);
    	clickSaveChangesButton();
        waitUntilPageLoad();
    }
}

