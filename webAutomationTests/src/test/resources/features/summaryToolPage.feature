Feature: Summary Tool
  Scenario:
    Given I am on the summary tool page
    When I click the upload icon
    And I click on a document to load
    And I click the Summarise button
    Then I should see a summary of the article displayed on the page