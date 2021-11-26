package web.tests.search;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.actions.SearchActions;
import web.base.TestBase;
import web.pages.SearchBar;
import web.pages.SearchResults;
import web.utils.Logger;
import web.utils.JsonReader;

/**
 * The test to verify result list can be sorted on demand
 * 
 */
public class SearchDepartments05 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="books")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-departments-no-results.json", "Books", 1);

	}
	
	// 2. Verify result list can be sorted on demand
	//   a. Perform a search with:
	//      i. Department: Books
	//     ii. Keyword: apple
	//    iii. Book Language: English
	//     iv. Change sort option to Publication date
	//   b. The Result is sorted by Publication date

	/**
	 * The test to verify result list can be sorted on demand
	 * 
	 * @param keyword the keyword to search for
	 */
	@Test(dataProvider = "books",
			groups = { "smoke"},
			description = "Verify Sort dropdown does not display when searching having no result found")
	public void searchDepartments05(final String keyword) {
		
		final SearchBar searchBar = new SearchBar();
		final SearchResults searchResults = new SearchResults();
		final SearchActions searchActions = new SearchActions();
		
		try {
			
			//   a. Perform a search with:
			//      i. Department: Books
			//     ii. Keyword: apple
			//    iii. Book Language: English
			//     iv. Change sort option to Publication date
			
			Logger.logInfo("a. Perform a search with department 'Books'"
					+ " with keyword '" + keyword + "'" 
					+ " and book language 'EN'");
			searchActions.searchPreparation("EN");	
			
			searchBar.searchDepartments("Books", keyword);
				
			// If no results message display
			boolean check = searchResults.checkIfNoResultsMessageDisplay("No results for");
		
			// If searching having no result found
			Logger.assertExtentTrue(check, "No results for " + keyword +" displays on Result Info area");
				
			// Verify there is no Sort dropdown displays on Results page
			check = searchResults.checkIfSortDropdownDisplay();
			Logger.assertExtentFalse(check, "  Sort dropdown does not display when searching having no result found");
			
			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
			
		} 
	}
	
}
