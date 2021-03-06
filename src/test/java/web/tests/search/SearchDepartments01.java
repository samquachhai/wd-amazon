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
public class SearchDepartments01 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="books")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-departments-results-more-16-items.json", "Books", 1);

	}
	
	// 1. Verify result list is paginated if there are more than 16 items
	//   a. Perform a search with:
	//     i. Department: Books
	//    ii. Keyword: apple
	//   iii. Book Language: English
	//   b. The Result displays exactly 16 items on each page.

	/**
	 * The test to verify result list is paginated if there are more than 16 items
	 * 
	 * @param keyword the keyword to search for
	 */
	@Test(dataProvider = "books",
			groups = { "smoke"},
			description = "Verify result list is paginated if there are more than 16 items")
	public void searchDepartments01(final String keyword) {
		
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
			
			// If there are more than 16 items displays on result list
			final boolean check = searchResults.checkIfMultipleResultsPaginationDisplay();
			Logger.assertExtentTrue(check, "There are more than 16 items displays on result list");
						
			// Verify all books containing keyword	
			Logger.logInfo("Verify all books containing keyword '" + keyword + "'");
			
			searchResults.verifyIfAllBooksNameContains(keyword);
			
			// Verify moving next paging page
			Logger.logInfo("Verify moving next paging page");
			
			searchResults.verifyIfNextPaginationWork(keyword);
			
			// Verify moving previous paging page
			Logger.logInfo("Verify moving previous paging page");
			
			searchResults.verifyIfPreviousPaginationWork(keyword);
		
			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
			
		}
	}
	
}
