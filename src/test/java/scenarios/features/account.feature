@account
Feature: Verify the Account Module in the Application

 @smoke @sanity
    Scenario: Verify the Login Functionality for existing number
    Given user launches the application
    And user clicks on the Account in the footer
    When user clicks the Login button
    And user enters the valid mobile number
    And user clicks the Continue button
    Then user enters the OTP
    And user validates otp result
    Then system should display the appropriate login status

  @smoke @sanity
  Scenario: Verify components of Account
    Then system should display the following components in the Account section
      | Profile            |
      | Manage Addresses   |
      | Saved Payments     |
      | Help & Support     |
      | About Us           |
      | Terms Of Use       |
      | Privacy Policy     |

  @smoke @sanity
  Scenario: Verify the Access Quick Actions
    Given system should display the quick actions options in the account section
      | Wishlist   |
      | My Orders  |
      | Wallet     |

  @smoke @sanity
  Scenario: Verify components of FCC page
    When system should display the following components in the FCC section
      | First Connect |
      | Silver Edge   |
      | Golden Glow   |
      | Platinum Aura |
      | Black Card    |

  @sanity
  Scenario: Verify CRUD on the Address page
    And user clicks on the my Profile option
    And user update their first name
    And user update their last name
    And user update their gender details
    And user clicks on the update changes
    And user validate that personal details successfully updated
    And user clicks on the manage addresses
    And user is able to add a new address
    And user enters the new first name
    And user enters the new last name
    And user enters the new number
    And user enters the new pin code
    And user enters the new address
    And user selects a address type as work
    And user clicks on the add address
    And user validate that new address added successfully
    And user updates an existing address
    And user updates their first name
    And user updates their last name
    And user updates their number
    And user updates their postal code
    And user updates their address
    And user clicks on the save changes
    And Validate that existing address has updated
    And user is able to delete exiting address
    And user clicks on the confirm remove button for delete address
    And user validate that delete address message is displayed
