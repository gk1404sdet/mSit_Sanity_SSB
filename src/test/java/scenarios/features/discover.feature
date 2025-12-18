@discover
Feature: Verify the Discover Modules in the Mobile Application

  #  Background:
#    Given user launches the application
#    And user taps on the Account in the footer
#    When user clicks the Login button
#    And user enters the valid mobile number
#    And user clicks the Continue button
#    Then user enters the OTP
#    And user validates otp result
#    Then system should display the appropriate login status

  Scenario: Verify the Categories Functionality
    Given user back to home page
#    Given user is on the homepage
    Given User clicks on Discover in the footer
    When user selects a skin type
    And user selects a cleansers
    And user validates that selected category page navigation
    And user back to home page
    Then user selects a personal care
    And user selects a hands and feet
    And user validates that selected category page navigation

  Scenario: Verify the Brands Functionality
    Given user back to home page
#    Given User clicks on Discover in the footer
    When user clicks on the brands
    And user selects a brand from customer favorites
    And user validates that selected brands navigation
    And user back to home page
    And user clicks on the brands
    Then user selects a brands from the trending
    And user validates that selected brands navigation