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
public class SearchDepartments04 extends TestBase {
	
	/**
	 * Data provider for test
	 * 
	 */
	@DataProvider(name="books")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-departments-results-more-16-items.json", "Books", 1);

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
			description = "Verify result list can be sorted on demand")
	public void searchDepartments04(final String keyword) {
		
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
				
			// If there are more than 16 items displays on result list
			final boolean check = searchResults.checkIfMultipleResultsPaginationDisplay();
			Logger.assertExtentTrue(check, "There are more than 16 items displays on result list");
			
			// If searching having results found
			searchResults.selectSortResultsDropdown("Publication Date"); 
			
			// Verify Publication Date is selected and display on Sort dropdown
			Logger.logInfo("Verify Publication Date is selected and display on Sort dropdown");
			
			final String selectedOption = searchResults.getSelectedSortResultsDropdownOption();			
			
			Logger.assertExtentEquals(selectedOption, "Publication Date");
			
			// Verify list books Publication date is sorted descending by default
			Logger.logInfo("Verify list books Publication date is sorted descending by default");
			
			searchResults.verifyListBooksPublicationDateSortedDescending();
							
			// Capture screenshot at last step
			Logger.logInfoWithScreenshot("");
			
		} catch (Exception ex) {
			Logger.logExceptionFail(ex.getMessage());
			
		} 
	}
	
}
