@checkout
Feature: Checkout Process and Order Placement

#  Background:
#    Given user launches the application
#    And user taps on the Account in the footer
#    When user clicks the Login button
#    And user enters the valid mobile number
#    And user clicks the Continue button
#    Then user enters the OTP
#    And user validates otp result
#    Then system should display the appropriate login status

  Scenario: Verify a successful payment
    Given user back to home page
#    Given user is on the homepage
    When user selects a makeup category from home page
    And user clicks the sub category from makeup page
    And user selects a product from makeup plp
    And User clicks on the add to bag
    And user clicks on the cart icon in the header
    And User clicks on the proceed to pay
    Then User clicks on the continue to payment
    And user selecting cod option
    And User clicks on the place order button
    And User validate that the order is placed successfully
    And User clicks on the continue shopping button

  Scenario: Failed Order Placement
    Given user is on the homepage
    When user selects a fragrance category from home page
    And user clicks the sub category from fragrance page
    And user selects a product from fragrance plp
    And User clicks on the add to bag
    And user clicks on the cart icon in the header
    And User clicks on the proceed to pay
    And User clicks on the continue to payment
    And user selecting UPI option
    And user enters the UPI id
    And user validates that UPI id
    And User validate that an error message indicating payment failure
