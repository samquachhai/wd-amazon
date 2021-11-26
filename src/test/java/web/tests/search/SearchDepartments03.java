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
public class SearchDepartments03 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="books")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-departments-no-results.json", "Books", 1);

	}
	
	// 1. Verify result list is paginated if there are more than 16 items
	//   a. Perform a search with:
	//     i. Department: Books
	//    ii. Keyword: apple
	//   iii. Book Language: English
	//   b. The Result displays exactly 16 items on each page.

	/**
	 * The test to verify notice message displays 
	 * when searching having no result found
	 * 
	 * @param keyword the keyword to search for
	 */
	@Test(dataProvider = "books",
			groups = { "smoke"},
			description = "Verify notice message displays when searching having no result found")
	public void searchDepartments03(final String keyword) {
		
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
			
			// If no results message display
			boolean check = searchResults.checkIfNoResultsMessageDisplay("No results for");
						
			// If searching having no result found
			Logger.assertExtentTrue(check, "No results for " + keyword +" displays on Result Info area");
			
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
