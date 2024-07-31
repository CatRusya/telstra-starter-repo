Feature: Sim Card Activator
  Describes the behavior of the Sim card activation microservice

  Scenario: Successful SIM card activation
    Given a successful SIM card
    When a request to activate the sim card is submitted
    Then the activation should be successful and its state should be recorded to the database

  Scenario: Failed SIM card activation
    Given a failed SIM card
    When a request to activate the sim card is submitted
    Then the activation should fail and its state should be recorded to the database