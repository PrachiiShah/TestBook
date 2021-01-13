#Author: ps242215@gmail.com
@Purchase
Feature: Purchase Plan and Payment Method
  @BuyPassPlan
  Scenario: View Purchase Plan Payment Methods
    Given all plans are displayed
    When user clicks on Buy Pass 
    Then payment methods are displayed
  @Payment_methods_card
  Scenario: user selects card payment
    Given user selects card payment
    When user enters card details
    Then clicks on pay
	@Payment_methods_upi
  Scenario: user selects upi payment
    Given user selects upi payment
    When user enters upi id
    Then clicks on pay gets error message
     