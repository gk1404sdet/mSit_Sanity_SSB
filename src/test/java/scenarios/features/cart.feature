@cart
Feature: Verify the Cart Page Functionality

#    @smoke @sanity
#  Scenario: Verify the sign-in functionality via phone number for an existing user
#      Given user launches the application
#      And user clicks on the Account in the footer
#      When user clicks the Login button
#      And user enters the valid mobile number
#      And user clicks the Continue button
#      Then user enters the OTP
#      And user validates otp result
#      Then system should display the appropriate login status

  @smoke @sanity
  Scenario: Adding the Product to cart
    Given user back to home page
    Given user is on the homepage
    When user selects a makeup category from home page
    And user clicks the sub category from makeup page
    And user selects a product from makeup plp
    And User clicks on the add to bag
    And user back to parent window

  @smoke @sanity
  Scenario: Adding the Product to my bag
#    Given user is on the homepage
    When user back to home page
#    When user selects a makeup category from home page
    And user clicks the sub category lips from makeup page
    And user selects a product from lips plp
    And user selects a product from makeup plp
    And User clicks on the add to bag
    And user back to parent window

  @smoke @sanity
  Scenario: Verify the components of the Bag
#    Given user is on the homepage
    When user back to home page
    When user clicks on the cart icon in the header
    And user validate that cart is not empty
    Then system should display the following components on the cart page
      | Bag          |
      | Address      |
      | Payment      |
      | Best coupons for you |

  @smoke @sanity
  Scenario: Verify increase and decrease of product quantity
#    Given user is on the homepage
#    When user clicks on the cart icon in the header
    And user validate that cart is not empty
    And user increase the product quantity in the cart
    And user decrease the product quantity in the cart

  @smoke @sanity
  Scenario: Verify adding/removing product to bag from PDP and move to Wishlist
#    Given user is on the homepage
#    When user clicks on the cart icon in the header
    And user validate that cart is not empty
    And user clicks on the product x mark
    And user moves a product to the wishlist from the cart
    And user validate that product moved to wishlist
    And user validate that cart is not empty
    Then user clicks on the product x mark
    And user removes a product to the wishlist from the cart
    And user validate that product removed from cart