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
public class SearchDepartments_02 extends TestBase {
	
	// Constructor
	public SearchDepartments_02() {}
	
	@DataProvider(name="books")
	public Object[][] passData() throws IOException, ParseException
	{
		return JsonReader.getData("src/test/resources/search-departments.json", "Books", 1);

	}
	
	// 2. Verify result list can be sorted on demand
	//   a. Perform a search with:
	//      i. Department: Books
	//     ii. Keyword: apple
	//    iii. Book Language: English
	//     iv. Change sort option to Publication date
	//   b. The Result is sorted by Publication date

	@Test(dataProvider = "books",
			groups = { "smoke"},
			description = "Verify result list can be sorted on demand")
	public void searchDepartments_02(String keyword) throws Exception {
		
		SearchBar searchBar = new SearchBar();
		SearchResults searchResults = new SearchResults();
		SearchActions searchActions = new SearchActions();
		
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
			String expectNoResultMessage = "No results for";
			
			boolean check = searchResults.checkIfNoResultsMessageDisplay(expectNoResultMessage);
			
			
			if(!check) {
				// If searching having results found
				searchResults.selectSortResultsDropdown("Publication Date"); 
				
				// Verify Publication Date is selected and display on Sort dropdown
				Logger.logInfo("Verify Publication Date is selected and display on Sort dropdown");
				
				String selectedSortResultsDropdownOption = searchResults.getSelectedSortResultsDropdownOption();			
				
				Logger.assertExtentEquals(selectedSortResultsDropdownOption, "Publication Date");
				
				// Verify list books Publication date is sorted descending by default
				Logger.logInfo("Verify list books Publication date is sorted descending by default");
				
				searchResults.verifyListBooksPublicationDateSortedDescending();
							
			} else {
			    
				// If searching having no result found
				Logger.assertExtentTrue(check, expectNoResultMessage + " displays on Result Info area");
				
				// Verify there is no Sort dropdown displays on Results page
				check = searchResults.checkIfSortDropdownDisplay();
				Logger.assertExtentFalse(check, " Sort dropdown does not display on Results page");
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
