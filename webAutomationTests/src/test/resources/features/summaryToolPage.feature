Feature: Summary Tool
  Scenario: Can upload and summarise pdf document
    Given I am on the summary tool page
    When I click the upload icon
    And I click on a document to load
    And I click the Summarise button
    Then I should see a summary of the article displayed on the page

  Scenario: Click summarise without uploading pdf document
    Given I am on the summary tool page
    When I click the Summarise button
    Then I should see an error message "No document uploaded"