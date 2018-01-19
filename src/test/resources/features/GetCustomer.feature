Feature: Get a Customer
  As a user of the system
  I want to get a customer in the system by id
  So I can see customer's information

  Scenario: Get a Customer
    Given the positive GET Customer scenario
    When I make a Get request to the system with an existing id
    Then I should get 200 response code and correct full information

  Scenario: Get a Customer Negative
     Given the negative GET Customer scenario
     When I make a Get request to the system with a non existing 123 id
     Then I should get 404 Not Found response code
