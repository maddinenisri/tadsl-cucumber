Feature: Lookup a definition
  In order to talk better
  As an English student
  I want to look up word definitions

  Scenario: Looking up the definition of 'apple'
    Given the user is on the Wikionary home page
    When the user looks up the definition of the word 'apple'
    Then they should see the definition 'Apple'

  Scenario: Looking up the definition of 'pear'
    Given the user is on the Wikionary home page
    When the user looks up the definition of the word 'pear'
    Then they should see the definition 'Pear'
