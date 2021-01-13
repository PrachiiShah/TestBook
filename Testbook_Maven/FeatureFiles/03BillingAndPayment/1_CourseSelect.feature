#Author: ps242215@gmail.com
@Course_Selection
Feature: Selecting a course
  @Course_Display
  Scenario Outline: Course list displayed
    Given user clicks on Courses tab
    When user selects any <course>
    Then user redirected to course page
    Examples:
    | course |
    | banking |    
	@Course_Purchase
	Scenario: User purchases a course
		Given user is on selected course page
		Then user clicks on Get TestBook Pass