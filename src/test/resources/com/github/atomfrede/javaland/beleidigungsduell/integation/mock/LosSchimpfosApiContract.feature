Feature: Los Schimpfos API

  Background:
    * url baseUrl

  Scenario: Test Get Beleidigung
    * path 'schimpfos-wortos.php'
    * method get
    * status 200
    * match response == '#string'
    * def beleidigung = response