#Author: ps242215@gmail.com
@TestSeries_Selection
Feature: Selecting test series
  @Series_Display
  Scenario: Series list displayed
    Given user is on Test series page
    When user views any test series
    Then user redirected to relevant series page
	@Series_Purchase
	Scenario: 
		When user is on selected series page
		Then user clicks on unlock now button
	@FreeSeriesDisplay
	Scenario:
		Given user clicks on Test series tab
		Then user is diplayed free test series list
		