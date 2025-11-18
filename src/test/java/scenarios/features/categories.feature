#@categories
#Feature: Verify the Categories Modules in the Mobile Application
#
#  Background:
#
#  Scenario Outline: Login as a user with a valid mobile number
#
#    When User clicks on the Account option in the footer
#    And User clicks the Login button
#    And User enters the mobile number "<mobile no>"
#    And User clicks the Proceed button
#    Then User enters the OTP
#    And User clicks on the Verify OTP button
#    And User validate that the user has successfully logged in
#
#    Examples:
#
#      | mobile no  |
##      | 8883371696 |
##      | 9871054681 |
#      | 9629318961 |
##      | 9606253725 |
##      | 9632653387 |
##      | 9790153971 |
#
#  Scenario: Verify the Categories Functionality
#
#    Given User clicks on Categories in the footer
#    When User selects men category
#    And User selects fragrance for men option
#    And User selects perfumes option
#    And User validate that the navigation to selected section
#    And User return to the categories page
#    Then User select gifts category
#    And User selects house warming gift
#    And User selects home decor
#    And User validate that selected brand navigation
#    And User return to the categories page
#    And User clicks on the Account option in the footer
#
#  Scenario: Logout functionality for a logged-in user
#
#    When User clicks on the Logout button
#    And User clicks on the yes button for logout
#    Then User validate that the user is logged out