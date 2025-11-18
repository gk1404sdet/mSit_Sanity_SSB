#@luxe
#Feature: Verify the Luxe Modules in the Mobile Application
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
#      | 8883371696 |
##      | 9629318961 |
##      | 9606253725 |
##      | 9632653387 |
##      | 9790153971 |
#
#  Scenario: Verify the Luxe Functionality
#
#    Given User is on the luxepage
#    When User clicks on a banner from the luxe
#    And User return to the luxe page
#    And User clicks a sunglasses from categories you will love section
#    And User selects a product from selected category PLP
#    And User validate that it navigates to the PDP
#    Then User return to the luxe page from PDP
#    And User selects a citizen brand from brands to look out for
#    And User validate that navigation
#    And User return to the luxe page
#    And User return to main page
#
#
#  Scenario: Logout functionality for a logged-in user
#
#    When User clicks on the Account option in the footer
#    When User clicks on the Logout button
#    And User clicks on the yes button for logout
#    Then User validate that the user is logged out
