/*
 * HomePage class
 *
 */

package web.pages;

import org.openqa.selenium.By;

import web.base.PageObject;
import web.utils.Constants;

public class HomePage extends PageObject {

	public static int timer = 10;	
	
	// Page elements locators
	final By yourAccoutLink = By.xpath("//*[@id='nav-link-accountList']");
	final By signInLink = By.xpath("//*[@id='nav-signin-tooltip']/a/span");
	final By languageSettingsLink = By.xpath("//*[@id='icp-nav-flyout']/span");
    
    
    // Constructor
    public HomePage() {}
    
 
    /**
   	 * This method is intended to navigate to Home page
     * 
   	 */
    public void navigateToHomePage() {
        navigateToURL(Constants.URL);
        waitUntilPageLoad();
        
    }

    /**
   	 * This method is intended to open Sign In page
     * 
   	 */

    public void navigateToSignInPage(){
        clickUsingActions(yourAccoutLink);
        waitUntilPageLoad();
    }
    
    /**
   	 * This method is intended to open Language Settings page
     * 
   	 */

    public void navigateToLanguageSettingsPage(){
        clickUsingActions(languageSettingsLink);
        waitUntilPageLoad();
    }
    
    /**
   	 * This method is intended to sign out logged in user
     * 
   	 */
    public void signOutWithSignOutLink(){
        navigateToURL(Constants.SIGNOUT_URL);
        waitUntilPageLoad();
    }
}

