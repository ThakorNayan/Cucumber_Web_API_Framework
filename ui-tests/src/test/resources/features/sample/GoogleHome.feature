@google @smoke
Feature: Google home searches

  Scenario: search for Google
    Given a user opens google home
    And user searches for "Google"

  Scenario: login
    Given test user logs into Google
    And supervisor logs into Google

