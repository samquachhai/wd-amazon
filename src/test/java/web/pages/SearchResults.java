/*
 * HomePage class
 *
 */

package web.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.TestException;

import web.base.PageObject;
import web.utils.Logger;

/**
 * SearchResults
 * 
 */
public class SearchResults extends PageObject {

	// Page elements locators
	By searchUpperResultInfoBar = By.xpath("//*[@id='search']//span[contains(@cel_widget_id,'UPPER-RESULT_INFO_BAR')]//div[@class='sg-col-inner']//span");
	By searchUpperResultInfoBar1 = By.xpath("//*[@id='search']//span[contains(@cel_widget_id,'UPPER-RESULT_INFO_BAR')]//div[@class='sg-col-inner']//span[1]");
	By searchUpperResultInfoBar2 = By.xpath("//*[@id='search']//span[contains(@cel_widget_id,'UPPER-RESULT_INFO_BAR')]//div[@class='sg-col-inner']//span[3]");
	By searchLeftRefinementsDepartments = By.xpath("//*[@id='search']//span[@cel_widget_id='LEFT-REFINEMENTS-0']//div[@id='departments']");
	By resultSortDropdownBox = By.xpath("//*[@id='search']//span[contains(@cel_widget_id,'UPPER-RESULT_INFO_BAR')]//select[@id='s-result-sort-select']");
	By resultSortDropdownLabel = By.xpath("//*[@id='a-autoid-0-announce']/span[@class='a-dropdown-label']");
	By resultSortDropdownPrompt = By.xpath("//*[@id='a-autoid-0-announce']/span[@class='a-dropdown-prompt']");
	By resultMainSlotList = By.xpath("//*[@id='search']//div[contains(@class, 's-main-slot s-result-list')]");
	By resultList = By.xpath("//*[@id='search']//span[contains(@cel_widget_id, 'MAIN-SEARCH_RESULTS')]//div[@class='a-section']");
	By resultFirstBookNameOnTop = By.xpath("//*[@id='search']//div[contains(@class, 's-main-slot s-result-list')]/div[1]//div[@class='a-section']/div[2]/div[2]//a/span");
	By previousButton = By.xpath("//*[@id='search']//span[contains(@cel_widget_id,'MAIN-PAGINATION')]//ul/li/a");
	By nextButton = By.xpath("//*[@id='search']//span[contains(@cel_widget_id,'MAIN-PAGINATION')]//ul/li[@class='a-last']/a");
	By selectedPagingIndex = By.xpath("//*[@id='search']//span[contains(@cel_widget_id,'MAIN-PAGINATION')]//ul/li[@class='a-selected']/a");
	By searchNoResultMessage = By.xpath("//*[@id='search']//span[contains(@cel_widget_id,'MAIN-TOP_BANNER_MESSAGE')]//span");
	By resultSortListPublication = By.xpath("//*[@id='search']//span[contains(@cel_widget_id, 'MAIN-SEARCH_RESULTS')]//div[@class='a-section']/div[2]/div[2]//h2/parent::div[1]/div/div");
	
	// Constructor
    public SearchResults() {
    	super();
    }
       
    /**
	 * This method is intended to get the first book name displaying on top current result page
	 * 
	 */
    public String getFirstBookNameOnTopOfCurrentResultPage() {	
    	return getText(resultFirstBookNameOnTop);

    }
    
    /**
	 * This method is intended to get total books displaying on current result page
	 * 
	 */
    public String getTotalBooksOnCurrentResultsPage() {
    	int totalBooks = getListBooksNameOnCurrentResultsPage().size();
    	return String.valueOf(totalBooks);

    }
    
    /**
	 * This method is intended to get list of books name displaying on current result page
	 * 
	 */
    public List<String> getListBooksNameOnCurrentResultsPage() {
    	List<WebElement> books = getElements(resultList);
    	List<String> booksList = new ArrayList<String>();

        for (WebElement element :books) {
            if (element == null) {
                throw new TestException("Some elements in the list do not exist");
            }
            
            WebElement bookName = element.findElement(By.xpath("./div[2]//a/span"));
            
            booksList.add(bookName.getText().trim());
            
        }
        return booksList;

    }
    
    /**
   	 * This method is intended to get the upper no result message
   	 * 
   	 * @return message result (e.g. No results for InvalidBook in Books.)
   	 */
    public String getSearchNoResultMessage() {	
    	List<String> messages = getElementsText(searchNoResultMessage);
    	String result = "";
    	
    	for(String message: messages) {
    		result = result + message + " ";
    	}
    	
    	return result;

    }
       
    /**
	 * This method is intended to get the upper result info having records in current page
	 * 
	 */
    public String getUpperResultsInfo() {	
    	List<String> messages = getElementsText(searchUpperResultInfoBar);
    	String result = "";
    	
    	for(String message: messages) {
    		result += message;
    	}
    	
    	return result;
     }
    
    /**
	 * This method is intended to get the paging items in upper result info 
	 * 
	 */
    public String getPagingItemsInUpperResultsInfo() {	
    	return getText(searchUpperResultInfoBar1);

    }
    
    /**
   	 * This method is intended to get the upper result info having search keyword
     *  
   	 */
    public String getKeywordInUpperResultsInfo() {  	
    	return getText(searchUpperResultInfoBar2);
    }
    
    /**
   	 * This method is intended to click Next at Pagination area.
     * 
     */
    public void clickNext() {
 
    	Logger.logInfo("Click Next ");
    	clickUsingActions(nextButton);
    	
    }
    
    /**
   	 * This method is intended to click Next at Pagination area.
     * 
     */
    public void clickPrevious() {
 
    	Logger.logInfo("Click Previous ");
    	clickUsingActions(previousButton);
    	
    }
    
    /**
   	 * This method is intended to get the current selected Search Departments text on Search Bar
     * 
   	 */
    public String getCurrentSelectPageIndex() {
    	return getText(selectedPagingIndex);
    } 

    /**
   	 * This method is intended to check if no result message display
   	 * 
   	 * @param expect the message to compare
     * 
   	 */
    public boolean checkIfNoResultsMessageDisplay(final String expect) {
    	try {
    		// Get current no result message
    		String actual = getSearchNoResultMessage();
    	
    		return actual.contains(expect);
    		
    	} catch(Exception e) {
    		// No message display
    		return false;
    	}
    }
    
    /**
   	 * This method is intended to check if there is only 1 page result after searching
   	 * 
   	 */
    public boolean checkIfMultipleResultsPaginationDisplay() {
    	try {
    		// Get current no result message
    		String actual = getPagingItemsInUpperResultsInfo();
    	
    		return actual.contains("of over");
    		
    	} catch(Exception e) {
    		// No message display
    		return false;
    	}
    }
    
    /**
   	 * This method is intended to check if all books name contain given text
   	 * 
   	 * @param text matching text to compare
     * 
   	 */
    public void verifyIfAllBooksNameContains(final String text) {
    	// Get all books display on current page
    	boolean check = true;
    	List<String> books = getListBooksNameOnCurrentResultsPage();
    	
    	for(final String book: books) {
    		if(!book.toLowerCase().contains(text.toLowerCase())
    			&& !text.toLowerCase().contains(book.toLowerCase())) {
    			// there is book name not containing text
    			check = false;
    			break;
    		}
    	}
    	
    	Logger.assertExtentTrue(check, "All books name contain '" + text + "'"); 
    }
    
    /**
   	 * This method is intended to check if clicking Next move to next page
   	 * 
   	 * @param text matching text to compare
     * 
   	 */
    public void verifyIfNextPaginationWork(final String text) {
    	// Get current paging state
    	int currentPageIndex = Integer.parseInt(getCurrentSelectPageIndex());
    	String currentFirstBookOnTop = getFirstBookNameOnTopOfCurrentResultPage();
    	String currentResultPageItems = getPagingItemsInUpperResultsInfo().split("of over")[0].trim(); 	
    	
    	// Click Next
    	clickNext();
    	
    	// Get current paging state after moving next page
    	int expectIndex = currentPageIndex + 1;
    	int currentPageIndexAfterMoveNext = Integer.parseInt(getCurrentSelectPageIndex());
    	String currentFirstBookOnTopAfterMoveNext = getFirstBookNameOnTopOfCurrentResultPage();
    	String currentResultPageItemsAfterMoveNext = getPagingItemsInUpperResultsInfo().split("of over")[0].trim();
    	
    	Logger.assertExtentEquals(currentPageIndexAfterMoveNext, expectIndex);
    	
    	Logger.assertExtentNotEquals(currentFirstBookOnTopAfterMoveNext, currentFirstBookOnTop);
    	
    	Logger.assertExtentNotEquals(currentResultPageItemsAfterMoveNext, currentResultPageItems);
    	
    	verifyIfAllBooksNameContains(text);
    	
    	/*
    	boolean check = true;
    	
    	if(currentPageIndexAfterMoveNext != expectIndex
    		|| currentFirstBookOnTopAfterMoveNext.equals(currentFirstBookOnTop)
    		|| currentResultPageItemsAfterMoveNext.equals(currentResultPageItems)) {
    		check = false;
    	}
    	
    	if (check) {
    		// check if all books name contain text in current page after moving next
    		check = checkIfAllBooksNameContains(text) ;
    	}
    	
    	return check;
    	*/
    }
    
    /**
   	 * This method is intended to check if clicking Next move to next page
   	 * 
   	 * @param text matching text to compare
     * 
   	 */
    public void verifyIfPreviousPaginationWork(final String text) {
    	// Get current paging state
    	int currentPageIndex = Integer.parseInt(getCurrentSelectPageIndex());
    	String currentFirstBookOnTop = getFirstBookNameOnTopOfCurrentResultPage();
    	String currentResultPageItems = getPagingItemsInUpperResultsInfo().split("of over")[0].trim();
    	
    	// Click Next
    	clickPrevious();
    	
    	// Get current paging state after moving previous page
    	int expectIndex = currentPageIndex - 1;
    	int currentPageIndexAfterMovePrevious = Integer.parseInt(getCurrentSelectPageIndex());
    	String currentFirstBookOnTopAfterMovePrevious = getFirstBookNameOnTopOfCurrentResultPage();
    	String currentResultPageItemsAfterMovePrevious = getPagingItemsInUpperResultsInfo().split("of over")[0].trim();
 
    	Logger.assertExtentEquals(currentPageIndexAfterMovePrevious, expectIndex);
    	
    	Logger.assertExtentNotEquals(currentFirstBookOnTopAfterMovePrevious, currentFirstBookOnTop);
    	
    	Logger.assertExtentNotEquals(currentResultPageItemsAfterMovePrevious, currentResultPageItems);
    	
    	verifyIfAllBooksNameContains(text);
    	
    	/*
    	boolean check = true;
    	
    	if(currentPageIndexAfterMoveNext != expect
    		|| currentFirstBookOnTopAfterMoveNext.equals(currentFirstBookOnTop)
    		|| currentResultPageItemsAfterMoveNext.equals(currentResultPageItems)) {
    		check = false;
    	}
    	
    	if (check) {
    		// check if all books name contain text in current page after moving next
    		check = checkIfAllBooksNameContains(text) ;
    	}
    	
    	return check;
    	*/
    }
    
    /**
   	 * This method is intended to check if Next button display at Paging area
   	 *  
   	 */
    public boolean checkIfNextButtonDisplay() {
    	return isElementPresent(nextButton);
    }
    
    /**
   	 * This method is intended to check if Previous button display at Paging area
   	 *  
   	 */
    public boolean checkIfPreviousButtonDisplay() {  	
    	return isElementPresent(previousButton);
    }

    //====== Sort ======
    
    /**
   	 * This method is intended to select all the Sort Results dropdown options that display text matching.
     * 
     * @param text the visible text to match against
   	 */
    public void selectSortResultsDropdown(final String text) {
 
    	Logger.logInfo("Select '" + text + "' from the Sort Results dropdown");
    	
    	// Select item in list
    	selectOptionsByText(resultSortDropdownBox, text);
    	waitUntilPageLoad();
    }
    
    /**
	 * This method is intended to get the current selected Sort Result dropdown option display on Results page
	 * 
	 */
    public String getSelectedSortResultsDropdownOption() {	
    	return getText(resultSortDropdownPrompt);

    }
    
    /**
   	 * This method is intended to check if Sort dropdown display on Results page
   	 *  
   	 */
    public boolean checkIfSortDropdownDisplay() {
    	return isElementPresent(nextButton);
    }
    
    /**
	 * This method is intended to get list of books Publication date displaying on current result page
	 * 
	 */
    public List<String> getListBooksPublicationDateOnCurrentResultsPage() {
    	List<WebElement> books = getElements(resultSortListPublication);
    	List<String> booksList = new ArrayList<String>();

        for (WebElement element :books) {
            if (element == null) {
                throw new TestException("Some elements in the list do not exist");
            }
            
            WebElement bookName = element.findElement(By.xpath("(./span)[last()]"));
            
            booksList.add(bookName.getText().trim());
            
        }
        return booksList;

    }
    
    /**
   	 * This method is intended to get list of books Publication date is sorted descending
   	 * 
   	 */
    public void verifyListBooksPublicationDateSortedDescending() {
    	List<String> datestring = getListBooksPublicationDateOnCurrentResultsPage();
    	
    	final DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
		final List<Date> dateList = new ArrayList<Date>();
		datestring.forEach((String d) -> {
			try {
				dateList.add(df.parse(d));
			} catch (ParseException e) {
			}
		});
		
		// Copy original date list then sort new list
		List<Date> dateListDateSorted = dateList;
		
		// sort descending
		Collections.sort(dateListDateSorted, (o1, o2) -> o2.compareTo(o1));
		
		Logger.assertExtentEquals(dateListDateSorted, dateList,
				"   List books publication date is not sorted descending.");
		
    }
    
    /**
   	 * This method is intended to get list of books Publication date is sorted ascending
   	 * 
   	 */
    public void verifyListBooksPublicationDateSortedAscending() {
    	List<String> datestring = getListBooksPublicationDateOnCurrentResultsPage();
    	
    	DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
		List<Date> dateList = new ArrayList<Date>();
		datestring.forEach((String d) -> {
			try {
				dateList.add(df.parse(d));
			} catch (ParseException e) {
			}
		});
		
		// Copy original date list then sort new list
		List<Date> dateListDateSorted = dateList;
		
		// sort descending
		Collections.sort(dateListDateSorted, (o1, o2) -> o1.compareTo(o2));
		
		Logger.assertExtentEquals(dateListDateSorted, dateList,
				"   List books publication date is not sorted ascending.");
		
    }
}

