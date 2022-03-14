Feature: Test the beleidungs application admin API

  Background:
    * def appUrl = baseUrls.bleidigungs_app
    * url appUrl

  Scenario: Test get all Beleidigungen and verify default parameters
    * path 'beleidigungen'
    * method get
    * status 200
    * match response == { _total_count: "#number", _params: { limit: 10, offset: 0}, items: "#array" }

    Scenario: Test get all Beleidigungen and verify custom parameters are used
      * path 'beleidigungen'
      * param limit = 21
      * param offset = 4
      * method get
      * status 200
      * match response._params == { limit: 21, offset: 4}
