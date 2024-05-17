Feature: Get book by ISBN

  Scenario Outline: User calls web service to get a book by its ISBN
    Given there is a book with the ISBN <isbn>
    When the book is fetched by its ISBN
    Then the status code is 200
    And the response should contain:
      | totalItems  | 1                |
      | kind        | books#volumes    |

    Examples:
      | isbn            | # Description                      |
      | 9781451648546   | # Steve Jobs by Walter Isaacson    |
      | 0451526538      | # The Great Gatsby, old ISBN-10    |
      | 9780131103627   | # The C Programming Language       |
      | 9780321573513   | # The Art of Computer Programming  |
      | 0345391802      | # The Hitchhiker's Guide to Galaxy |
      | 9780671027032   | # How to Win Friends & Influence   |
