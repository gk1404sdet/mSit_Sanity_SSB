#@home
#Feature: Home Page Functionality
##
##  Background:
##
##  Scenario Outline: Login as a user with a valid mobile number
##
##    When User clicks on the Account option in the footer
##    And User clicks the Login button
##    And User enters the mobile number "<mobile no>"
##    And User clicks the Proceed button
##    Then User enters the OTP
##    And User clicks on the Verify OTP button
##    And User validate that the user has successfully logged in
##
##    Examples:
##
##      | mobile no  |
##      | 8883371696 |
##      | 9629318961 |
##      | 9606253725 |
##      | 9632653387 |
##      | 9790153971 |
#
#  Scenario: Verify the Home Page Functionality
#
#    Given User is on the homepage
#    When User clicks on a banner from the homepage
#    And User return to the home page
#    And User clicks a kidswear from categories you will love section
#    And User selects a product
#    And User validate that it navigates to the PDP
#    And User return to the home page from PDP
#    Then User clicks on Luxe Swipe
#    And User validate that navigation
#    And User return to the home page
#
#  Scenario: Verify the Product Listing Page Functionality
#
#    Given User is on the homepage
#    And User clicks a kidswear from categories you will love section
#    And User adds a product to the wishlist from PLP
#    And User validate that product added to wishlist
#    And User remove a product to the wishlist from PLP
#    And User validate that product removed from wishlist
#    And User selects a product
#    And User validate that it navigates to the PDP
#    And User return to the PLP from PDP
#    Then User clicks on the sort option
#    And User selects a sort option as price low to high
#    Then User clicks on the sort option
#    And User selects a sort option as price high to low
#    And User validate that product sorted product is displayed
#    And User clicks on the filter option
#    And User select the product type as sweatshirts
#    And User selects the brands as life
#    And User clicks on the show items
#    And User validate that products are displayed as per filter
#    And User is removing all the filter
#    And User return to the home page
#
#  Scenario: Verify the Product Detail Page Functionality
#
#    When User clicks a kidswear from categories you will love section
#    And User selects a product
#    And User validate that it navigates to the PDP
#    And User validate that product added to wishlist
#    And User remove a product to the wishlist from PDP
#    And User validate that product removed from wishlist
#    Then User select the size
#    And User clicks on the add to bag
#    And User clicks on the view bag
#    And User return to PDP
#
#  Scenario: Verify the Pincode check functionality with an invalid Pincode
#
#    When User enters an invalid pincode for delivery
#    And User clicks on the check option
#    And User sees a message indicating to enter a valid pincode
#    And User select the size
#    And User clicks on the add to bag
#    And User clicks on the view bag
#    Then User enters an invalid pincode for delivery
#    And User clicks on the check option
#    And User sees a message indicating to enter a valid pincode
#    And User return to home page
#
##  Scenario: Logout functionality for a logged-in user
##
##    When User clicks on the Account option in the footer
##    When User clicks on the Logout button
##    And User clicks on the yes button for logout
##    Then User validate that the user is logged out
