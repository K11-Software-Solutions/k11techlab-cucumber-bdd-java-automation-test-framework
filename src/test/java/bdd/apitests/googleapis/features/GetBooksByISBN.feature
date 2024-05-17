Feature: Get book by ISBN

  Scenario Outline: Retrieve a book using its ISBN
    Given there is a book with the ISBN 9781451648546
    When the book is fetched by its ISBN
    Then the status code is 200
    And the response should contain:
      | totalItems | 1                |
      | kind       | books#volumes    |
    And response includes the following details in any order:
      | items.volumeInfo.title        | Steve Jobs         |
      | items.volumeInfo.publisher    | Simon and Schuster |
