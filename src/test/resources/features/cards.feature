Feature: Manage jokers
  In order to maintain the almanac
  As an API user
  I want to create, retrieve and delete joker cards

  Scenario: Create a joker
    Given a card with name "Hanging Chad" and description "Retrigger first played card used in scoring 2 additional times"
    When the card is saved
    Then the card is persisted successfully

  Scenario: Delete a joker
    Given an existing card with name "Photograph" and description "First played face card gives X2 Mult when scored"
    When the card is deleted
    Then the card no longer exists

  Scenario: Get non-existing joker
    When I request a card with id 999
    Then the response status should be 404

  Scenario: Delete non-existing joker
    When I delete a card with id 999
    Then the response status should be 404

  Scenario: Get all jokers
    Given an existing card with name "Mail-In Rebate" and description "Earn $5 for each discarded [rank], rank changes every round"
    When I request all cards
    Then the response status should be 200

  Scenario: Create joker with invalid body
    When I create a card with invalid payload
    Then the response status should be 500

  Scenario: Create invalid joker
    Given a card with name "" and description ""
    Then the card is not created

