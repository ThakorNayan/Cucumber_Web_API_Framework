@stackoverflow @smoke
Feature: Stackoverflow test

  Background:
    Given user opens stack overflow home page

  Scenario: validate stack overflow login icon
    Then validate login button is displayed

  Scenario: validate stack overflow search is working
    When user searches for "Java" in stackoverflow
    Then validate header text is "Human verification"

  Scenario Outline: validate stack overflow search is working
    When user searches for "<keyword>" in stackoverflow
    Then validate header text is "<headerText>"
    Examples:
      | keyword | headerText         |
      | Java    | Human verification |
      | C#      | Human verification |

