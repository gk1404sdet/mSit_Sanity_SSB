#@header
#Feature: Verify the Header Functionality
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
##      |  9871054681 |
##      | 9790153971 |
#
#  Scenario: Verify the Wishlist Functionality
#
#    Given User is on the homepage
#    When User clicks on the wishlist icon in the header
#    And User validate that wishlist icon navigate to the wishlist page
#    And User select a product from wishlist page
#    And User validate that it navigates to the PDP
#    And User return to the wishlist page
#    And User adds a product to the cart from the wishlist page
#    And User selects a size
#    And User clicks on the add to bag
#    And User validate that product add to cart successfully
#    Then User removes a product from the wishlist page
#    And User clicks on the yes button for removing product from wishlist
#    And User validate that product removed from wishlist
#    And User return to the home page
#
#  Scenario: Verify the Cart Functionality
#
#    When User clicks on the cart icon in the header
#    And User validate that cart icon navigate to the cart page
#    And User increase the product quantity in the cart
#    And User decrease the product quantity in the cart
#    And User clicks on the product
#    And User moves a product to the wishlist from the cart
#    And User validate that product moved to wishlist
#    And User clicks on the product
#    And User removes a product to the wishlist from the cart
#    And User validate that product removed from cart
#    And User clicks on the gift wrap
#    Then User enters the receiver name
#    And User enters the gift message
#    And User enters the sender name
#    And User clicks the save gift details button
#    And User validate the gift wrap added successfully
#    And User return to the home page
#
#  Scenario: Verify the Search Functionality
#
#    When User clicks on the search bar
#    When User search with keywords
#    And User validate that search option navigation to the relevant products
#    And User return to the search page
#    And User clicks on trending products
#    And User validate that selected product navigate to pdp
#    Then User clicks on popular brands
#    And User validate that selected brand navigation to the relevant brand
#    And User back to back home
##
##  Scenario: Verify the Store and Events Functionality
##
##    When User clicks on Store and Events icon in the header
##    When User clicks on the drop down option for select city
##    And User selects the city
##    And User validate that get store directions option is displayed
##    Then User click on the Events tab
##    Then User selects the city
##    And User validate that available events details
##    And User return to the home page
##
##  Scenario: Logout functionality for a logged-in user
##
##    When User clicks on the Account option in the footer
##    When User clicks on the Logout button
##    And User clicks on the yes button for logout
##    Then User validate that the user is logged out
#
#
