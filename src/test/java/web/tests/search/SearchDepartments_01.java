package web.tests.search;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.actions.SearchActions;
import web.base.TestBase;
import web.pages.SearchBar;
import web.pages.SearchResults;
import web.utils.JsonReader;
import web.utils.Logger;

/**
 * The test to verify result list is paginated if there are more than 16 items
 * 
 */
public class SearchDepartments_01 extends TestBase {
	
	// Constructor
	public SearchDepartments_01() {}
	
	@DataProvider(name="books")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-departments.json", "Books", 1);

	}
	
	// 1. Verify result list is paginated if there are more than 16 items
	//   a. Perform a search with:
	//     i. Department: Books
	//    ii. Keyword: apple
	//   iii. Book Language: English
	//   b. The Result displays exactly 16 items on each page.

	@Test(dataProvider = "books",
			groups = { "smoke"},
			description = "Verify result list is paginated if there are more than 16 items")
	public void searchDepartments_01(String keyword) throws Exception {
		
		SearchBar searchBar = new SearchBar();
		SearchResults searchResults = new SearchResults();
		SearchActions searchActions = new SearchActions();
		
		try {
			
			// a. Perform a search with:
			//     i. Department: Books
			//    ii. Keyword: apple
			//   iii. Book Language: English
			Logger.logInfo("a. Perform a search with department 'Books'"
					+ " with keyword '" + keyword + "'" 
					+ " and book language 'EN'");
			searchActions.searchPreparation("EN");	
			
			searchBar.searchDepartments("Books", keyword);
			
			// If no results message display
			String expectNoResultMessage = "No results for";
									
			boolean check = searchResults.checkIfNoResultsMessageDisplay(expectNoResultMessage);
			
			
			if(!check) {
				// If searching having results found
				
				check = searchResults.checkIfMultipleResultsPaginationDisplay();
				
				if(check) {
					// Searching having more than 1 paging
					
					// Verify all books containing keyword	
					Logger.logInfo("Verify all books containing keyword '" + keyword + "'");
					
					searchResults.verifyIfAllBooksNameContains(keyword);
					
					// Verify moving next paging page
					Logger.logInfo("Verify moving next paging page");
					
					searchResults.verifyIfNextPaginationWork(keyword);
					
					// Verify moving previous paging page
					Logger.logInfo("Verify moving previous paging page");
					
					searchResults.verifyIfPreviousPaginationWork(keyword);
				
				} else {
					
					// Verify there is no button Next display on Paging area
					check = searchResults.checkIfNextButtonDisplay();
					Logger.assertExtentFalse(check, " Button Next does not display on Paging area");
					
					// Verify there is no button Previous on Paging area
					check = searchResults.checkIfPreviousButtonDisplay();
					Logger.assertExtentFalse(check, " Button Previous does not display on Paging area");
				}
				
			} else {
			
				// If searching having no result found
				Logger.assertExtentTrue(check, expectNoResultMessage + " displays on Result Info area");
				
				// Verify there is no button Next display on Paging area
				check = searchResults.checkIfNextButtonDisplay();
				Logger.assertExtentFalse(check, " Button Next does not display on Paging area");
				
				// Verify there is no button Previous on Paging area
				check = searchResults.checkIfPreviousButtonDisplay();
				Logger.assertExtentFalse(check, " Button Previous does not display on Paging area");
			}
			
			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
			
		} finally {
			closeBrowser();
		}
	}
	
}
