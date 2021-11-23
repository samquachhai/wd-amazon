package web.actions;

import web.pages.HomePage;
import web.pages.LanguageSettingsPage;

/**
 * SearchActions
 *  
 */
public class SearchActions {

	/**
   	 * This method is intended to navigate to Home page, open Language Settings, select prefer language and save changes
     * 
   	 */
	public void searchPreparation(String languageCountryCode) {
		navigateToHomePage();
		navigateToLanguageSettingsPage();
		selectAndSavePreferLanguage(languageCountryCode);
	}
	
	/**
   	 * This method is intended to select prefer language and save changes
     * 
   	 */
	public void selectAndSavePreferLanguage(String languageCountryCode){
    	LanguageSettingsPage languageSettings = new LanguageSettingsPage();
    	languageSettings.selectAndSavePreferLanguage(languageCountryCode);
    	
    }
	
	/**
   	 * This method is intended to navigate to Home page
     * 
   	 */
	public void navigateToHomePage(){
        HomePage homePage = new HomePage();
        homePage.navigateToHomePage();      
    }

	/**
   	 * This method is intended to navigate to Language Settings page
     * 
   	 */
	public void navigateToLanguageSettingsPage(){
        HomePage homePage = new HomePage();
        homePage.navigateToLanguageSettingsPage();      
    }
	
}
