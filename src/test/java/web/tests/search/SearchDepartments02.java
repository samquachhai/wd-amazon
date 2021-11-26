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
public class SearchDepartments02 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="books")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-departments-results-less-16-items.json", "Books", 1);

	}
	
	// 1. Verify result list is paginated if there are more than 16 items
	//   a. Perform a search with:
	//     i. Department: Books
	//    ii. Keyword: apple
	//   iii. Book Language: English
	//   b. The Result displays exactly 16 items on each page.

	/**
	 * The test to verify result list is not paginated if there are more than 16 items
	 * 
	 * @param keyword the keyword to search for
	 */
	@Test(dataProvider = "books",
			groups = { "smoke"},
			description = "Verify result list is not paginated if there are less than 16 items")
	public void searchDepartments02(final String keyword) {
		
		final SearchBar searchBar = new SearchBar();
		final SearchResults searchResults = new SearchResults();
		final SearchActions searchActions = new SearchActions();
		
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
			
			// If there are less than 16 items displays on result list
			final boolean checkMultipleResults = searchResults.checkIfMultipleResultsPaginationDisplay();
			final boolean checkNoResults = searchResults.checkIfNoResultsMessageDisplay("No results for");
			
			
			boolean check = (checkMultipleResults == checkNoResults) ? true : false;
			
			Logger.assertExtentTrue(check, "There are less than 16 items displays on result list");
						
			// Verify there is no button Next display on Paging area
			check = searchResults.checkIfNextButtonDisplay();
			Logger.assertExtentFalse(check, " Button Next does not display on Paging area");
			
			// Verify there is no button Previous on Paging area
			check = searchResults.checkIfPreviousButtonDisplay();
			Logger.assertExtentFalse(check, " Button Previous does not display on Paging area");
			
			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
			
		}
	}
	
}
