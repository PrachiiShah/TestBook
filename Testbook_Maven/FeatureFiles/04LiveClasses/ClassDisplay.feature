#Author: ps242215@gmail.com
@ViewLiveClass
Feature: Available live classes
  @LiveClassList
  Scenario: Display live classes
    Given user clicks on Live Classes Tab
    When user select any live class
    Then live video is displayed