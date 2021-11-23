/*
 * HomePage class
 *
 */

package web.pages;

import org.openqa.selenium.By;

import web.base.PageObject;
import web.utils.Logger;

public class SearchBar extends PageObject {

	public static int timer = 10;
		
	// Page elements locators
	By searchDropdownCard = By.xpath("//*[@id='nav-search-dropdown-card']");
	By searchDropdownBox = By.xpath("//*[@id='nav-search-dropdown-card']//div[contains(@class, 'nav-sprite')]//select");
	By searchTextBox = By.xpath("//input[@id='twotabsearchtextbox']");
	By searchSubmitButton = By.xpath("//input[@id='nav-search-submit-button']");
	By searchDropdownLabel = By.xpath("//span[@id='nav-search-label-id']");
	
    // Constructor
    public SearchBar() {}
    
    /**
	 * This method is intended to search information from given departments area
	 *
	 * @param department the department area to search for (E.g. Books)
	 * @param keyword the keyword to search for (E.g. apple)
	 * 
	 */
    public void searchDepartments(String department, String keyword) {
        // Select department area
		selectSearchDepartmentsDropdown(department);
    	
    	// Enter keyword to search for
    	enterSearchTextBox(keyword);
    	
    	// Click Search button
    	clickSearchButton();
    }

    /**
	 * This method is intended to enter keyword in the Search box on Search Bar
	 *
	 * @param keyword the keyword to search for (E.g. apple)
	 * 
	 */
    public void enterSearchTextBox(String keyword) {
    	Logger.logInfo("Enter '" + keyword + "' in the Search box on Search Bar");
    	setText(searchTextBox, keyword); 
    }
    
    /**
   	 * This method is intended to click Search button on Search Bar
     *  
   	 */
    public void clickSearchButton() {  	
		Logger.logInfo("Click Search button on Home page");
		clickUsingActions(searchSubmitButton);      
    	waitUntilPageLoad();
    }
    
    /**
   	 * This method is intended to select all the Departments dropdown options that display text matching.
     * 
     * @param text the visible text to match against
   	 */
    public void selectSearchDepartmentsDropdown(String text) {
 
    	Logger.logInfo("Select '" + text + "' from the Departments dropdown");
    	// Open list item
    	clickUsingActions(searchDropdownCard);
    	
    	// Select item in list
    	selectOptionsByText(searchDropdownBox, text);
    	
    }
    
    /**
   	 * This method is intended to get Search button text on Search Bar
     * 
   	 */
    public String getSearchButtonText() {
    	return getText(searchSubmitButton);
    }
    
    /**
   	 * This method is intended to get the current selected Search Departments text on Search Bar
     * 
   	 */
    public String getSearchDropdownLabel() {
    	return getText(searchDropdownLabel);
    } 

}

