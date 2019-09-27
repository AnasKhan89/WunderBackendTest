Feature: Create User

Scenario Outline: create user

Given perform a POST request to create user
When verify the status '<code>'
Then verify user is created with "<name>" "<salary>" and "<age>"

Examples:
|name| salary|age|code|
|owais2| 123| 29 |200|


  Scenario Outline: verify that user is created

    Given perfom a get request to get user by id
    When verify the status '<code>'
    Then verify user is exist with "<name>"

    Examples:
    |code|name|
    |200 |owais2|
