package web.pages;

import org.openqa.selenium.By;

import web.base.PageObject;
import web.utils.Constants;

/**
 * HomePage
 * 
 */
public class HomePage extends PageObject {

	// Page elements locators
	final By yourAccoutLink = By.xpath("//*[@id='nav-link-accountList']");
	final By signInLink = By.xpath("//*[@id='nav-signin-tooltip']/a/span");
	final By languageSettings = By.xpath("//*[@id='icp-nav-flyout']/span");
    
    // Constructor
	public HomePage () {
		super();
	}
	
    /**
   	 * This method is intended to navigate to Home page
     * 
   	 */
    public void navigateToHomePage() {
        navigateToUrl(Constants.URL);
        waitUntilPageLoad();
        
    }

    /**
   	 * This method is intended to open Sign In page
     * 
   	 */

    public void navigateToSignInPage(){
        clickUsingActions(this.yourAccoutLink);
        waitUntilPageLoad();
    }
    
    /**
   	 * This method is intended to open Language Settings page
     * 
   	 */

    public void navigateToLanguageSettingsPage(){
        clickUsingActions(this.languageSettings);
        waitUntilPageLoad();
    }
    
    /**
   	 * This method is intended to sign out logged in user
     * 
   	 */
    public void signOutWithSignOutLink(){
        navigateToUrl(Constants.SIGNOUT_URL);
        waitUntilPageLoad();
    }
}

